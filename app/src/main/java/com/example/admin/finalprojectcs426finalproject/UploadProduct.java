package com.example.admin.finalprojectcs426finalproject;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class UploadProduct extends AppCompatActivity {
    Products addedproductWithseri;
    DatabaseReference salesreference;
    private static final int PICK_IMAGE_REQUEST = 1;
    private int REQUEST_CODE=1;
    StorageReference mStorageRef;
    EditText productdescription;
    ImageView productimg;
    EditText productname;
    EditText productprice;
    EditText productsaleprice;
    EditText productstock;
    EditText productmaincl;
    Spinner producttype;
    RecyclerView recyclerView;
    Button productupload;
    ArrayList<Uri> mArr=new ArrayList<>();
    Bitmap currentBitmap;
    Dialog mydialog;
    Button addpic;
    DatabaseReference mref;
    DatabaseReference mDatabaseRef;
    private Uri mImageUri;

    private StorageTask mUploadTask;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_product);
        mStorageRef= FirebaseStorage.getInstance().getReference("uploads");

        i=new Intent();


        mref= FirebaseDatabase.getInstance().getReference("PRODUCTS");
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("uploads");
        salesreference= FirebaseDatabase.getInstance().getReference("SALES");



        init();

        productupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(productname.getText()==null)
                    Toast.makeText(UploadProduct.this, "Tên sản phẩm không được để trống", Toast.LENGTH_SHORT).show();
                else
                if(productprice.getText()==null)
                    Toast.makeText(UploadProduct.this, "Giá sản phẩm không được để trống", Toast.LENGTH_SHORT).show();
                else
                if(productstock.getText()==null)
                    Toast.makeText(UploadProduct.this, "Tồn không được để trống", Toast.LENGTH_SHORT).show();
                else
                {
                    //BẮT ĐẦU CHO VÀO DATABASE
                    String pname=productname.getText().toString();
                    String pprice=productprice.getText().toString();
                    String pstock=productstock.getText().toString();
                    String ppricesale=productsaleprice.getText().toString();
                    String pmain=productmaincl.getText().toString();
                    String pdescription=productdescription.getText().toString();
                    String ptype=String.valueOf(producttype.getSelectedItem());

                    //INSERT PRODUCT VÀO TABLE PRODUCTS OFFLINE.
                    //      dbhelper.insertValueForProducts(db,id,pname,ptype,Integer.parseInt(ppricesale),Integer.parseInt(pprice),pmain,Integer.parseInt(pstock));


                    //INSERT PRODUCT VÀO TABLE PRODUCTS - ONLINE
                    String key=mref.push().getKey();
                    ProdctsWithoutSeri addedproduct=new ProdctsWithoutSeri(key,pname,ppricesale,pprice,Integer.parseInt(pstock),pmain,ptype,pdescription);
                    mref.child(key).setValue(addedproduct);

                    //INSERT PICTURE VÀO TABLE PICTUREDATA ONLINE

                    addedproductWithseri=new Products(key,pname,ppricesale,pprice,Integer.parseInt(pstock),pmain,ptype,pdescription);
                    uploadFile(addedproduct.id,addedproduct);

                    //INSERT VAO DATABASE SALES
                    String keysale=salesreference.push().getKey();
                    String userID= MainActivity.getUserId();
                    SaleInfo saleInfo=new SaleInfo(userID,addedproduct.id);
                    salesreference.child(keysale).setValue(saleInfo);

                    //-------------------------------- KẾT THÚC -------------------------------


                }
            }
        });
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    void uploadFile(String id, ProdctsWithoutSeri addedproduct)
    {
        final String pid=id;

        if (mImageUri != null) {
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri);


            Task<Uri> urlTask = mUploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        Upload upload = new Upload(pid,
                                downloadUri.toString());
                        String uploadId = mDatabaseRef.push().getKey();
                        mDatabaseRef.child(uploadId).setValue(upload);
                        i.putExtra("URLback",upload.getmImageURL());
                        i.putExtra("datasendback",addedproductWithseri);
                        setResult(RESULT_OK,i);
                        finish();
                        // Toast.makeText(UploadProduct.this, "Insert data for Offline Pic", Toast.LENGTH_SHORT).show();
                        // db=dbhelper.getReadableDatabase();
                        //dbhelper.insertValueForPicture(db,Integer.parseInt(upload.mID),upload.getmImageURL());
                        //db=dbhelper.getReadableDatabase();
                        //Toast.makeText(UploadProduct.this, "Complete", Toast.LENGTH_SHORT).show();
                        //Cursor cusor=db.rawQuery("SELECT * FROM PICTUREDATA",null);

//                        Toast.makeText(UploadProduct.this, "PIC in upload: "+Integer.toString(cusor.getCount()), Toast.LENGTH_SHORT).show();




                    } else {
                        // Handle failures
                        // ...
                    }
                }
            });

        }
        else
            Toast.makeText(this, " null", Toast.LENGTH_SHORT).show();
    }



    void init()
    {
        productdescription=findViewById(R.id.Upload_Description);
        productimg=findViewById(R.id.Upload_img);
        productname=findViewById(R.id.Upload_name);
        productprice=findViewById(R.id.Upload_price);
        productsaleprice=findViewById(R.id.Upload_price_on_sale);
        productstock=findViewById(R.id.Upload_Stock);
        productmaincl=findViewById(R.id.Upload_MainColor);
        producttype=findViewById(R.id.Upload_type);
        productupload=findViewById(R.id.Upload_upload);
        currentBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        mydialog=new Dialog(UploadProduct.this);
        mydialog.setContentView(R.layout.popup_dialog);
        mydialog.setTitle("Choose your action");

        Button camera=(Button)mydialog.findViewById(R.id.opencamera);
        Button openfromgal=(Button)mydialog.findViewById(R.id.getfromlib);


        addpic=findViewById(R.id.addpictureBtn);
        addpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getfromlib=new Intent();
                getfromlib.setType("image/*");
                getfromlib.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(getfromlib,REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        //Lấy ảnh từ intent Camera của mình về dưới dạng bitmap và hiển thị lên imageview của mình
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            {
                try
                {

                    bitmap = MediaStore.Images.Media.getBitmap(UploadProduct.this.getContentResolver(), data.getData());

                } catch (IOException e)
                {
                    e.printStackTrace();
                }
                if(data.getData()!=null)
                {
                    mImageUri = data.getData();
                    productimg.setImageBitmap(bitmap);
                }
                else
                    Toast.makeText(this, "NULL", Toast.LENGTH_SHORT).show();
                //productimg.setImageBitmap(photo);
            }

        }

    }
    //đổi bitmap sang bytes
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // đổi bytes sang bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
}
