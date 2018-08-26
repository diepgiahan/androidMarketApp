package com.example.admin.finalprojectcs426finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    TextView notiChatTextView, notiCartTextView;
    notification notiNoti, notiChat, notiCart;

    Button addproduct;
    //CỦA TÂN THÊM VÀO
    final static String USER_ID="TEST USER ID";
    ArrayList<Products> suggestedArr = new ArrayList<>();
    static ArrayList<Upload> secondAr = new ArrayList<>();
    ArrayList<Upload> uploadArr = new ArrayList<>();
    static ArrayList<Products> puttoCart = new ArrayList<>();
    DatabaseReference databaseReference;
    DatabaseReference picturedatabaseReferece;
    RecyclerView recyclerView;
    RecycleViewAdapter adapter;

    Database database = new Database();

    public static String getUserId() {
        return USER_ID;
    }

    ArrayList<Products> thoitrangarr = new ArrayList<>();
    ArrayList<Products> thucphamarr = new ArrayList<>();
    ArrayList<Products> vanphongphamarr = new ArrayList<>();
    ArrayList<Products> dientuarr = new ArrayList<>();
    ArrayList<Products> dodunggiadinharr = new ArrayList<>();
    ArrayList<Products> noithatarr = new ArrayList<>();
    ArrayList<Products>nhaccuarr = new ArrayList<>();
    ArrayList<Products>dangoaiarr = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ~ Initialize search task object
        SearchTask search_task = new SearchTask(this);
        setFlashSaleContainer();
        setCart(this);
        setNotificationInitialNumber();
        setAccount(this);
        addproduct = findViewById(R.id.addProduct);
        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,UploadProduct.class);
                startActivityForResult(i,2);

            }
        });

        //CỦA TÂN - THÊM DỮ LIỆU VÀO SUGGESTEDARR (Array này chứa thông tin của toàn bộ Products được kéo về trên database ONLINE)
        databaseReference= FirebaseDatabase.getInstance().getReference("PRODUCTS");
        picturedatabaseReferece= FirebaseDatabase.getInstance().getReference("uploads");
        getOfflineDB();

        //KHÚC NÀY LÀ ĐỂ SET VIEW LÊN THỬ COI HIỆN HẾT ARRAY KHÔNG
        recyclerView=(RecyclerView)findViewById(R.id.reycleview);
        adapter = new RecycleViewAdapter(getApplicationContext(), suggestedArr, R.layout.cardview_layout, new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Products item) {
                //Toast.makeText(getApplicationContext(), "Mainact:"+item.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,DetailProductActivity.class);
                intent.putExtra("incart", item);
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        onClickCataloge();
    }
    private void setAccount(final Context context) {
        /*ImageView account = (ImageView) findViewById(R.id.main_account);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AccountActivity.class);
                startActivity(intent);
            }
        });*/
    }
    private void setNotificationInitialNumber() {
        //@todo link notification with user database ~ user class
        notiChatTextView = (TextView) findViewById(R.id.notification_number_chat);
        notiChat = new notification(notiChatTextView, "chat");
        notiCartTextView = (TextView) findViewById(R.id.notification_number_cart);
        notiCart = new notification(notiCartTextView, "cart");
        /**@todo notification
         * Notification class:
         *      get initial notification number from database -> have to save..
         *      if create new user -> initial notification = 0
         * Use notification class:
         *      show notification: show popup and run .showNotification()
         *      1 more/less cart item etc. -> add item and run .increse/decreaseNoti()
         */
    }
    void setFlashSaleContainer(){
        FlashSaleContainer = findViewById(R.id.FlashSaleContainer);
        int slides[] = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3, R.drawable.slide4, R.drawable.slide5, R.drawable.slide6};
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

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            suggestedArr.clear();
            if(dataSnapshot.exists()) {
                int i=0;
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    ProdctsWithoutSeri product=snapshot.getValue(ProdctsWithoutSeri.class);
                    suggestedArr.add(new Products(product.id,product.name,product.newprice,product.oldprice,product.quantity,product.maincolour,product.type,product.description));
                    database.addProduct(new Products(product.id,product.name,product.newprice,product.oldprice,product.quantity,product.maincolour,product.type,product.description));
                    //suggestedArr.get(suggestedArr.size()-1).setRes(secondAr.get(i).getmImageURL());
                    //Log.d("THÔNG BÁO THÔNG BÁO: ","Size of Arr:"+Integer.toString(suggestedArr.size()));
                    //Log.d("THÔNG BÁO THÔNG BÁO: ","Size of Upload:"+Integer.toString(uploadArr.size()));
                    //Log.d("THÔNG BÁO THÔNG BÁO: ","Upload link:"+(uploadArr.get(i).mImageURL));
                    i++;
                }

                adapter.notifyDataSetChanged();
                setSpecificArr();
                //database.setProduct_data(suggestedArr);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
    ValueEventListener getPictureListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
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

    void getOfflineDB() {
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
        picturedatabaseReferece.addListenerForSingleValueEvent(getPictureListener);
    }

    void StartAndBackToMain() {

    }

    void setSpecificArr() {
        for (int i=0;i<suggestedArr.size();i++) {
            switch(suggestedArr.get(i).type) {
                case "Thời Trang":
                    thoitrangarr.add(suggestedArr.get(i));
                    break;
                case "Thực Phẩm":
                    thucphamarr.add(suggestedArr.get(i));
                    break;
                case "Văn Phòng Phẩm":
                    vanphongphamarr.add(suggestedArr.get(i));
                    break;
                case "Đồ Dùng Gia Đình":
                    dodunggiadinharr.add(suggestedArr.get(i));
                    break;
                case "Nội Thất":
                    noithatarr.add(suggestedArr.get(i));
                    break;
                case "Nhạc Cụ":
                    nhaccuarr.add(suggestedArr.get(i));
                    break;
                case "Dã Ngoại":
                    dangoaiarr.add(suggestedArr.get(i));
                    break;
                case "Điện Tử":
                    dientuarr.add(suggestedArr.get(i));
                    break;
                default:
            }
        }
    }

    void onClickCataloge() {
        final ImageView thoitrang = findViewById(R.id.thoitrang);
        thoitrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = new RecycleViewAdapter(getApplicationContext(), thoitrangarr, R.layout.cardview_layout, new RecycleViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Products item) {
                        Intent intent = new Intent(MainActivity.this,DetailProductActivity.class);
                        intent.putExtra("incart", item);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });

        final ImageView thucpham = findViewById(R.id.thucpham);
        thucpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = new RecycleViewAdapter(getApplicationContext(), thucphamarr, R.layout.cardview_layout, new RecycleViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Products item) {
                        Intent intent = new Intent(MainActivity.this,DetailProductActivity.class);
                        intent.putExtra("incart", item.getId());
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });

        final ImageView vanphongpham = findViewById(R.id.vanphongpham);
        vanphongpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = new RecycleViewAdapter(getApplicationContext(), vanphongphamarr, R.layout.cardview_layout, new RecycleViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Products item) {
                        Intent intent = new Intent(MainActivity.this,DetailProductActivity.class);
                        intent.putExtra("incart", item);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });

        final ImageView dientu = findViewById(R.id.dientu);
        dientu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = new RecycleViewAdapter(getApplicationContext(), dientuarr, R.layout.cardview_layout, new RecycleViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Products item) {
                        Intent intent = new Intent(MainActivity.this,DetailProductActivity.class);
                        intent.putExtra("incart", item);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });

        final ImageView dodunggiadinh = findViewById(R.id.dodunggiadinh);
        dodunggiadinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = new RecycleViewAdapter(getApplicationContext(), dodunggiadinharr, R.layout.cardview_layout, new RecycleViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Products item) {
                        Intent intent = new Intent(MainActivity.this,DetailProductActivity.class);
                        intent.putExtra("incart", item);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });

        final ImageView noithat = findViewById(R.id.noithat);
        noithat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = new RecycleViewAdapter(getApplicationContext(), noithatarr, R.layout.cardview_layout, new RecycleViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Products item) {
                        Intent intent = new Intent(MainActivity.this,DetailProductActivity.class);
                        intent.putExtra("incart", item);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });


        final ImageView nhaccu = findViewById(R.id.nhaccu);
        nhaccu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = new RecycleViewAdapter(getApplicationContext(), nhaccuarr, R.layout.cardview_layout, new RecycleViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Products item) {
                        Intent intent = new Intent(MainActivity.this,DetailProductActivity.class);
                        intent.putExtra("incart", item);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });

        final ImageView dangoai = findViewById(R.id.dangoai);
        dangoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = new RecycleViewAdapter(getApplicationContext(), dangoaiarr, R.layout.cardview_layout, new RecycleViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Products item) {
                        Intent intent = new Intent(MainActivity.this,DetailProductActivity.class);
                        intent.putExtra("incart", item);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2 &&resultCode==RESULT_OK) {
            suggestedArr.add((Products)data.getExtras().getSerializable("datasendback"));
            String backurl=data.getExtras().get("URLback").toString();
            suggestedArr.get(suggestedArr.size()-1).setRes(backurl);
            secondAr.add(new Upload(((Products) data.getExtras().getSerializable("datasendback")).id,backurl));
            adapter.notifyDataSetChanged();
        }
    }
}