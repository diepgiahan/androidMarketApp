package com.example.admin.finalprojectcs426finalproject;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.admin.finalprojectcs426finalproject.Account.notification;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewFlipper FlashSaleContainer;
    ListView CategoryContainer;

    //CỦA TÂN THÊM VÀO
    final static String USER_ID="TEST USER ID";
    ArrayList<Products> suggestedArr=new ArrayList<>();
    static ArrayList<Upload> secondAr=new ArrayList<>();
    ArrayList<Upload> uploadArr=new ArrayList<>();
    static ArrayList<Products> puttoCart=new ArrayList<>();
    DatabaseReference databaseReference;
    DatabaseReference picturedatabaseReferece;
    RecyclerView recyclerView;
    RecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ~ Initialize search task object
        SearchTask search_task = new SearchTask(this);
        setFlashSaleContainer();
        setCart(this);
        setAccount(this);
        //CỦA TÂN - THÊM DỮ LIỆU VÀO SUGGESTEDARR (Array này chứa thông tin của toàn bộ Products được kéo về trên database ONLINE)
        databaseReference= FirebaseDatabase.getInstance().getReference("PRODUCTS");
        picturedatabaseReferece= FirebaseDatabase.getInstance().getReference("uploads");
        getOfflineDB();
        TextView notiNotiTextView = (TextView) findViewById(R.id.notification_number_notification);
        notification notiNoti = new notification(notiNotiTextView, "notification");
        notiNoti.setNotificationNumber(15);
        //KHÚC NÀY LÀ ĐỂ SET VIEW LÊN THỬ COI HIỆN HẾT ARRAY KHÔNG
       /** recyclerView=(RecyclerView)findViewById(R.id.reycleview);
        adapter=new RecycleViewAdapter(getApplicationContext(), suggestedArr, R.layout.cardview_layout, new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Products item) {
            }
        });

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

**/
    }

    void setFlashSaleContainer(){
        FlashSaleContainer = findViewById(R.id.FlashSaleContainer);
        int slides[] = {R.drawable.slide1,R.drawable.slide2,R.drawable.slide3,R.drawable.slide4,R.drawable.slide5,R.drawable.slide6};
        for (int slide : slides){
            setImageForFlashSaleContainer(slide);
        }
    }
    void setImageForFlashSaleContainer(int image){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(image);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        FlashSaleContainer.addView(imageView);
        FlashSaleContainer.setFlipInterval(2000);
        FlashSaleContainer.setAutoStart(true);
        FlashSaleContainer.setInAnimation(this,android.R.anim.slide_in_left);
        FlashSaleContainer.setOutAnimation(this,android.R.anim.slide_out_right);
    }
    private void setAccount(final Context context) {
        ImageView account = (ImageView) findViewById(R.id.main_account);
        Toast.makeText(context, "94", Toast.LENGTH_LONG).show();
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "97", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, AccountActivity.class);
                startActivity(intent);
            }
        });
    }
    void setCart(final Context context){
        ImageView imageView = (ImageView)findViewById(R.id.CartIcon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,CartPopUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void searchForSomething(View view) {
        Intent intent = new Intent(getApplicationContext(),SearchPopUpActivity.class);
        startActivity(intent);
    }

    ValueEventListener valueEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            suggestedArr.clear();
            if(dataSnapshot.exists())
            {
                int i=0;
                for (DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    ProdctsWithoutSeri product=snapshot.getValue(ProdctsWithoutSeri.class);
                    suggestedArr.add(new Products(product.id,product.name,product.newprice,product.oldprice,product.quantity,product.maincolour,product.type));
//                    suggestedArr.get(suggestedArr.size()-1).setRes(secondAr.get(i).getmImageURL());
                    //Log.d("THÔNG BÁO THÔNG BÁO: ","Size of Arr:"+Integer.toString(suggestedArr.size()));
                    //Log.d("THÔNG BÁO THÔNG BÁO: ","Size of Upload:"+Integer.toString(uploadArr.size()));
                    //Log.d("THÔNG BÁO THÔNG BÁO: ","Upload link:"+(uploadArr.get(i).mImageURL));
                    i++;

                }
                //adapter.notifyDataSetChanged();

            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
    ValueEventListener getPictureListener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            if(dataSnapshot.exists())
            {
                for (DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    Upload upload=snapshot.getValue(Upload.class);
                    uploadArr.add(new Upload(upload.mID,upload.getmImageURL()));
                    secondAr.add(new Upload(upload.mID,upload.getmImageURL()));
                    // dbhelper.insertValueForPicture(db,Integer.parseInt(upload.mID),upload.getmImageURL());
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    void getOfflineDB()
    {
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
        picturedatabaseReferece.addListenerForSingleValueEvent(getPictureListener);
    }

}