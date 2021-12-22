package org.aplas.latihanretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import models.Repo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.GitHubService;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.txtData);
        editText = findViewById(R.id.input_username);


//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.github.com")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        GitHubService service = retrofit.create(GitHubService.class);
//        Call<List<Repo>> repos = service.listRepos("khoirulromadhon");
//
//        repos.enqueue(new Callback<List<Repo>>() {
//            @Override
//            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
//                Integer id  = response.body().get(1).getId();
//                String htmlUrl = response.body().get(1).getHtmlUrl();
//                String description = response.body().get(1).getDescription();
//
//                Log.d("LatihanRetrofit", id  + " : " + htmlUrl + " : " + description);
//                textView.setText("ID = " + id + "\n" + htmlUrl + "\n" + "description = " + description);
//            }
//
//            @Override
//            public void onFailure(Call<List<Repo>> call, Throwable t) {
//                Log.e("Error", t.getMessage());
//            }
//        });
//        setContentView(R.layout.activity_main);
    }

    public void GetDataButton(View v){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mengambil Data");
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);

        String username = editText.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Repo>> repos = service.listRepos(username);

        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Integer id  = response.body().get(1).getId();
                String htmlUrl = response.body().get(1).getHtmlUrl();
                String description = response.body().get(1).getDescription();

                Log.d("LatihanRetrofit", id  + " : " + htmlUrl + " : " + description);
                textView.setText("ID = " + id + "\n" + htmlUrl + "\n" + "description = " + description);
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });

    }
}