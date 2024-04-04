package com.example.newsports;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newsports.Adapter.HomeAdapter;
import com.example.newsports.Adapter.PopularAdapter;
import com.example.newsports.Adapter.RecommendedAdapter;
import com.example.newsports.Adapter.ViewAllAadpter;
import com.example.newsports.models.HomeCategory;
import com.example.newsports.models.PopularModel;
import com.example.newsports.models.RecommendedModel;
import com.example.newsports.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;


public class Store extends Fragment {


    FirebaseFirestore firebaseFirestore;
    RecyclerView popularRec,homeCatRec,recommendedRec;
    FirebaseStorage db;

    //popular items
    List<PopularModel> popularModelList;
    PopularAdapter popularAdapter;

    //search view
    EditText search_box;
    private List<ViewAllModel> viewAllModelList;
    private RecyclerView recyclerViewsearch;
    private ViewAllAadpter viewAllAadpter;


    //home category
    List<HomeCategory> categoryList;
    HomeAdapter homeAdapter;

    //recommendation
    List<RecommendedModel> recommendedModelList;
    RecommendedAdapter recommendedAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_store, container, false);


        firebaseFirestore = FirebaseFirestore.getInstance();


//popular item

        popularRec=root.findViewById(R.id.pop_rec);
        homeCatRec=root.findViewById(R.id.explore_rec);
        recommendedRec=root.findViewById(R.id.recommended_rec);


        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularModelList=new ArrayList<>();
        popularAdapter=new PopularAdapter(getActivity(),popularModelList);
        popularRec.setAdapter(popularAdapter);

        firebaseFirestore.collection("Users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {


                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularModel popularModel=document.toObject(PopularModel.class);
                            popularModelList.add(popularModel);
                            popularAdapter.notifyDataSetChanged();}
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//explore/home category
        homeCatRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryList=new ArrayList<>();
        homeAdapter=new HomeAdapter(getActivity(),categoryList);
        homeCatRec.setAdapter(homeAdapter);

        firebaseFirestore.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {


                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HomeCategory homeCategory=document.toObject(HomeCategory.class);
                                categoryList.add(homeCategory);
                                homeAdapter.notifyDataSetChanged();}
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        //recommendation
        recommendedRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        recommendedModelList=new ArrayList<>();
        recommendedAdapter=new RecommendedAdapter(getActivity(),recommendedModelList);
        recommendedRec.setAdapter(recommendedAdapter);

        firebaseFirestore.collection("recommendation")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {


                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                RecommendedModel recommendedModel =document.toObject(RecommendedModel.class);
                                recommendedModelList.add(recommendedModel);
                               }
                            recommendedAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //searchbar
        recyclerViewsearch=root.findViewById(R.id.search_rec);
        search_box=root.findViewById(R.id.search_box);
        viewAllModelList=new ArrayList<>();
        viewAllAadpter=new ViewAllAadpter(getContext(),viewAllModelList);
        recyclerViewsearch.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewsearch.setAdapter(viewAllAadpter);
        recyclerViewsearch.setHasFixedSize(true);
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty()){
                    viewAllModelList.clear();
                    viewAllAadpter.notifyDataSetChanged();
                }else{
                    searchProduct(s.toString());
                }

            }


        });

        return root;

    }

    private void searchProduct(String type) {
        if(!type.isEmpty() ){
            firebaseFirestore.collection("AllProducts").whereEqualTo("type",type).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful() && task.getResult()!=null){
                        viewAllModelList.clear();
                        viewAllAadpter.notifyDataSetChanged();
                        for(DocumentSnapshot doc:task.getResult().getDocuments()){
                            ViewAllModel viewAllModel=doc.toObject(ViewAllModel.class);
                            viewAllModelList.add(viewAllModel);
                            viewAllAadpter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

    }
}