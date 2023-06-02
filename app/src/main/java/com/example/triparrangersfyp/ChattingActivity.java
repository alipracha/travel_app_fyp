package com.example.triparrangersfyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.triparrangersfyp.Adapter.ChatAdapter;
import com.example.triparrangersfyp.Adapter.CustomerAdapter;
import com.example.triparrangersfyp.Admin.Manage_Users;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.Tourist.ShowSeatsActivity;
import com.example.triparrangersfyp.Tourist.Users_Dashboard;
import com.example.triparrangersfyp.TravelAgency.AddTrip;
import com.example.triparrangersfyp.TravelAgency.Detailed_Trip;
import com.example.triparrangersfyp.TravelAgency.TA_Dashboard;
import com.example.triparrangersfyp.model.Booking;
import com.example.triparrangersfyp.model.Chat;
import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.model.Trips;
import com.example.triparrangersfyp.service.AddTripService;
import com.example.triparrangersfyp.service.DeleteChatService;
import com.example.triparrangersfyp.service.DeleteTripAPIService;
import com.example.triparrangersfyp.service.GetAllCustomerService;
import com.example.triparrangersfyp.service.GetMessageChatService;
import com.example.triparrangersfyp.service.InsertBookingService;
import com.example.triparrangersfyp.service.OnMessageClick;
import com.example.triparrangersfyp.service.SendChatMessageService;
import com.example.triparrangersfyp.service.SendImageChatService;
import com.example.triparrangersfyp.util.TinyDB;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Part;

public class ChattingActivity extends AppCompatActivity {

    ListView LV;
    List<Chat> MessagesList = new ArrayList<>();
    ImageView Attach;
    Button send, ViewMember;
    TextView GrpName;
    EditText Msg;
    ChatAdapter adapter;
    public static int CHATID = 0;
    ProgressDialog progressDialog;
    Chat chat;
    String getTripTitle, getChatID;
    TinyDB tinyDB;
    int getTripID;
    File file;
    private Uri filePath;
    boolean isCameraClicked = false;
    boolean isGalleryImgClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        LV = findViewById(R.id.Chat_ListView);
        Attach = findViewById(R.id.attachment);
        send = findViewById(R.id.SendMsg);
        Msg = findViewById(R.id.ChatMessage);

        ViewMember = (Button) findViewById(R.id.ViewMembers);

        GrpName = findViewById(R.id.GrpName);

        tinyDB = new TinyDB(this);
        GetMessages();
        getTripID = getIntent().getIntExtra("v1", 0);
        getTripTitle = getIntent().getStringExtra("v2");


        GrpName.setText(getTripTitle);
        Attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(ChattingActivity.this);

            }
        });

        ViewMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChattingActivity.this, ManageMembers.class);
                intent.putExtra("TRIP_ID", getTripID);
                startActivity(intent);

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = Msg.getText().toString();
                if (msg.isEmpty()) {
                    Toast.makeText(ChattingActivity.this,
                            "You Cannot Send Empty Message", Toast.LENGTH_SHORT).show();
                } else {
                    if (tinyDB.getInt("LOGIN_PREF") == 1) {
                        sendMessage(tinyDB.getInt("CUSTOMER_ID"), "c");
                    } else if (tinyDB.getInt("LOGIN_PREF") == 2) {
                        sendMessage(tinyDB.getInt("TRAVEL_AGENCY_ID"), "t");
                    }

                }
            }
        });


    }

    private void GetMessages() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.show();

        GetMessageChatService service = RetrofitClient.getClient().create(GetMessageChatService.class);
        Call<JsonObject> call = service.getMessages();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());

                        JSONArray jsonArray = jsonObject.getJSONArray("records");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject data = jsonArray.getJSONObject(i);
                            if (data.getInt("Trip_ID") == getTripID) {
                                MessagesList.add(new Chat(
                                        data.getInt("Chat_ID"),
                                        data.getString("Chat_Message"),
                                        data.getInt("Chat_SenderID"),
                                        data.getInt("Chat_ReceiverID"),
                                        data.getString("sender_type"),
                                        data.getString("Imagee"),
                                        data.getInt("Trip_ID")
                                ));
                            }

                        }
                        adapter = new ChatAdapter(MessagesList,
                                ChattingActivity.this, new OnMessageClick() {
                            @Override
                            public void OnItemCLick(int pos) {
                                CHATID = MessagesList.get(pos).getChat_ID();
                                new android.app.AlertDialog.Builder(ChattingActivity.this).setIcon(R.drawable.ic_baseline_warning_24)
                                        .setTitle("Exit").setMessage("Are you sure you want to Delete?")
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Delete_Chat();
                                            }
                                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).show();
                            }
                        });
                        LV.setAdapter(adapter);


                    } catch (JSONException e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ChattingActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void UploadImage() {
        chat = new Chat();
        progressDialog = new ProgressDialog(ChattingActivity.this);
        progressDialog.setTitle("Uploading Image");
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        if (filePath == null) {
            Toast.makeText(this, "No Image Added", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        } else {
            if (isCameraClicked) {
                file = new File(String.valueOf(filePath));
            } else {
                file = new File(getRealPathFromURI(filePath));
            }
            //creating request body for file

            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
            RequestBody SenderIDD = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(tinyDB.getInt("CUSTOMER_ID")));
            RequestBody Chat_ReceiverIDD = RequestBody.create(MediaType.parse("text/plain"), "0");
            RequestBody sender_typeE = RequestBody.create(MediaType.parse("text/plain"), "c");
            RequestBody Trip_IDD = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(getTripID));
            MultipartBody.Part body = MultipartBody.Part.createFormData("Imagee", file.getName(), requestFile);

            SendImageChatService service = RetrofitClient.getClient().create(SendImageChatService.class);
            Call<Chat> call = service.SendImage(SenderIDD, Chat_ReceiverIDD, sender_typeE, Trip_IDD, body);
            call.enqueue(new Callback<Chat>() {
                @Override
                public void onResponse(Call<Chat> call, Response<Chat> response) {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        chat = response.body();
                        if (chat != null) {
                            boolean error = chat.isError();
                            if (!error) {
                                Toast.makeText(ChattingActivity.this,
                                        chat.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ChattingActivity.this,
                                        chat.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ChattingActivity.this,
                                    chat.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(ChattingActivity.this, chat.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Chat> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(ChattingActivity.this,
                            t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, contentUri, proj,
                null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    private void sendMessage(int ID, String type) {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();

        SendChatMessageService service = RetrofitClient.getClient().create(SendChatMessageService.class);
        Call<Chat> call = service.InsertMessage(Msg.getText().toString(), ID,
                0
                , type, getTripID);

        call.enqueue(new Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                if (response.isSuccessful()) {
                    chat = response.body();
                    if (!chat.isError()) {

                        progressDialog.dismiss();
                        Toast.makeText(ChattingActivity.this, "Message Sent", Toast.LENGTH_SHORT).show();

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(ChattingActivity.this, chat.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(ChattingActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ChattingActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu_group, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.DeleteMessage:
                Delete_Chat();
                return true;
            default:
                return super.onContextItemSelected(item);

        }
    }

    private void Delete_Chat() {
        chat = new Chat();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();


        DeleteChatService service = RetrofitClient.getClient().create(DeleteChatService.class);
        Call<Chat> call = service.DeleteChat(CHATID);


        call.enqueue(new Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    chat = response.body();
                    if (!chat.isError()) {
                        Toast.makeText(ChattingActivity.this,
                                chat.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ChattingActivity.this,
                                chat.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ChattingActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        isCameraClicked = true;
                        isGalleryImgClicked = false;
                        filePath = data.getData();
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");

                        Attach.setImageBitmap(selectedImage);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        isCameraClicked = false;
                        isGalleryImgClicked = true;
                        filePath = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();
                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                Attach.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }
                        UploadImage();

                    }
                    break;
            }
        }
    }
}