package com.example.fireecommerceapp.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.fireecommerceapp.Adapter.CategoryAdapter;
import com.example.fireecommerceapp.Adapter.NewProductAdapter;
import com.example.fireecommerceapp.Adapter.PopularProductAdapter;
import com.example.fireecommerceapp.R;
import com.example.fireecommerceapp.activity.ShowAllActivity;
import com.example.fireecommerceapp.models.CatagoryModel;
import com.example.fireecommerceapp.models.NewProductModel;
import com.example.fireecommerceapp.models.PopularProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment
{
    //88.
    TextView catSeeAll,popularSeeAll,newProductSeeAll;

    //75.LinearLayout for slider
    LinearLayout linearLayouthome;
    //72.for ProgressDialog
    ProgressDialog progressDialog;

    //43*
        RecyclerView catRecyclerView;

        //58.
        RecyclerView newProductRecyclerView;
        //61.
        RecyclerView popularRecyclerview;

        //43 *. category recyclerView
    CategoryAdapter categoryAdapter;
    List<CatagoryModel> categorymodellist;

    //58.*NewProduct RecyclerView create the ref of adapter
    NewProductAdapter newProductAdapter;
    List<NewProductModel>  newProductModelsList;

    //61* Popular Product
    PopularProductAdapter popularProductAdapter;
    List<PopularProductModel> popularProductModelList;


    //46.FirebaseFirestore
    FirebaseFirestore db;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //31. Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_home, container, false);//

        //47.
        db = FirebaseFirestore.getInstance();

        //73.for ProgressDialog
        progressDialog = new ProgressDialog(getActivity());

        //44.get the view of recycle
        catRecyclerView = root.findViewById(R.id.rec_category);//

        //58*
        newProductRecyclerView = root.findViewById(R.id.new_product_rec);

        //61*
        popularRecyclerview = root.findViewById(R.id.popular_rec);

        //88*
        catSeeAll = root.findViewById(R.id.category_seeall);
        popularSeeAll = root.findViewById(R.id.popular_see_all);
        newProductSeeAll = root.findViewById(R.id.newProduct_seeAll);

        //89.
        catSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });
        //90.
        popularSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });
        //91.
        newProductSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });//now create ShowAllActivity, design in show_activity.xml, the create layout for recycler

        //75*.LinearLayout for slider
        linearLayouthome = root.findViewById(R.id.home_layout);
        linearLayouthome.setVisibility(View.GONE);

        //32. Image slider to load the images
        ImageSlider imageSlider = root.findViewById((R.id.image_slider));
        List <SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.cart,"Discount on Shoes", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.cart,"Discount on Shoes", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.cart,"Discount on Shoes", ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(slideModels);

        //74.for ProgressDialog
        progressDialog.setTitle("Welcome to My Ecommerce app");
        progressDialog.setMessage("Please wait....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();//

        //45.set the layoutManager
        catRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        //get reff
        categorymodellist = new ArrayList<>();
        //CategoryAdapter ke constructor me value pass kar di
        categoryAdapter = new CategoryAdapter(getContext(),categorymodellist);
        //set adapter on recyclerview
        catRecyclerView.setAdapter(categoryAdapter);//now cloude firebase setting

        //48.to read data from firebaseFireStore
        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //get document the model class bind data which come in model class first
                                CatagoryModel catagoryModel = document.toObject(CatagoryModel.class);
                                //get the list in the  arrayList using constructor
                                categorymodellist.add(catagoryModel);
                                categoryAdapter.notifyDataSetChanged();
                                //75**.LinearLayout for slider
                                linearLayouthome.setVisibility(View.VISIBLE);//now created DetailedActivity

                                //74*for ProgressDialog
                                progressDialog.dismiss();
                            }
                        } else {

                        }
                        }
                    });//now create layout new_product.xml

        //59.New Product
        //set LayoutManager
          newProductRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
            newProductModelsList = new ArrayList<>();
            newProductAdapter = new NewProductAdapter(getContext(),newProductModelsList);
            newProductRecyclerView.setAdapter(newProductAdapter);

        //60.to read data from firebaseFireStore
        db.collection("NewProduct")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //get document the model class bind data which come in model class first
                                NewProductModel newProductModel = document.toObject(NewProductModel.class);
                                //get the list in the  arrayList using constructor
                                newProductModelsList.add(newProductModel);
                                newProductAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
        //70.popular product
        popularRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
        //popularRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularProductModelList = new ArrayList<>();
        popularProductAdapter = new PopularProductAdapter(getContext(),popularProductModelList);
        popularRecyclerview.setAdapter(popularProductAdapter);

        //71.to read data from firebaseFireStore
        db.collection("AllProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //get document the model class bind data which come in model class first
                                PopularProductModel popularProductModel = document.toObject(PopularProductModel.class);
                                //get the list in the  arrayList using constructor
                                popularProductModelList.add(popularProductModel);
                                popularProductAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });


                        return root;
                    }
                }
