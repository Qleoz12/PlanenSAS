package com.planensas.myapplication;

import android.animation.Animator;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.planensas.myapplication.Activities.ClientList;
import com.planensas.myapplication.Entities.Cliente;
import com.planensas.myapplication.Entities.Models.Login;
import com.planensas.myapplication.ViewModels.ClienteViewModel;
import com.planensas.myapplication.services.ClienteRequests;
import com.planensas.myapplication.services.DataServiceGenerator;
import com.planensas.myapplication.utils.AppVault;
import com.planensas.myapplication.utils.UtilData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {
    ClienteViewModel clienteViewModel;
    private static final String TAG = "MyActivity";
    private ImageView bookIconImageView;
    private TextView bookITextView,skypeTextView;
    private ConstraintLayout rootView;
    private Button loginButton;
    private EditText email,password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //views
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.wait_activity);
        initViews();
        fisrtStateAnimation();
        //delay
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //MainActivity
                setContentView(R.layout.activity_main);
                initViews2();

            }
        }, 3000);

        //data
        clienteViewModel = ViewModelProviders.of(MainActivity.this).get(ClienteViewModel.class);
        clienteViewModel.getAllClientes().observe((LifecycleOwner) this, new Observer<List<Cliente>>() {
            @Override
            public void onChanged(@Nullable List<Cliente> notes) {

            }
        });

    }

    private void initViews() {
        //animation
        bookIconImageView = findViewById(R.id.IconImageView_wait);
        bookITextView = findViewById(R.id.TextView_wait);
        rootView = findViewById(R.id.rootView);

    }

    private void initViews2()
    {
        //UI
        loginButton= findViewById(R.id.loginButton);
        email= findViewById(R.id.emailEditText);
        password= findViewById(R.id.passwordEditText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UtilData.validEmail(email.getText().toString()))
                {
                    Log.v(TAG, "LOGIN [valid email address]");
                    login(email.getText().toString(),password.getText().toString());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"INVALID email address",Toast.LENGTH_SHORT).show();
                }


            }
        });

        skypeTextView= findViewById(R.id.skipTextView);
        skypeTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void onClick(View view)
            {
                //datatest encryption
                String user="test@plannen.com";
                String pass="Test123";
                AppVault v= new AppVault(MainActivity.this);
                v.saveUser(user);
                v.savepass(pass);

                Intent intent = new Intent(MainActivity.this, ClientList.class);
                intent.putExtra(UtilData.Extra_userid,3);
                startActivity(intent);
            }
        });
    }

    private void fisrtStateAnimation()
    {
        //delay
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //Loading
                bookITextView.setVisibility(GONE);
                rootView.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorSplashText));
                bookIconImageView.setImageResource(R.mipmap.ic_logo_gray_plannensas);
                startAnimation();

            }
        }, 1500);

    }
    private void startAnimation() {
        ViewPropertyAnimator viewPropertyAnimator = bookIconImageView.animate();
        //calcs
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Float imagexpoint= Float.valueOf((float) ((width*0.04)+8));

        viewPropertyAnimator.x(imagexpoint);
        viewPropertyAnimator.y(30f);
        viewPropertyAnimator.setDuration(1500);
        viewPropertyAnimator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void login(String email,String password) {
        DataServiceGenerator DataServiceGenerator = new DataServiceGenerator();
        ClienteRequests service = DataServiceGenerator.createService(ClienteRequests.class);
        Call<Login> call = service.loginApi(email, password);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Log.v(TAG, "LOGIN [isSuccess"+response.isSuccessful()+"]");
                if (response.isSuccessful())
                {
                    if (response != null){
                        Login loginList = response.body();
                        Log.v(TAG, "LOGIN [isSuccess "+loginList.getSucces()+"][getUserId "+loginList.getUserId()+"]");

                    }
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t)
            {
                Log.v(TAG,"LOGIN ERROR->"+ t.getMessage());
            }
        });
    }
    private void fetch()
    {
        DataServiceGenerator DataServiceGenerator = new DataServiceGenerator();
        ClienteRequests service = DataServiceGenerator.createService(ClienteRequests.class);
        //get the value of other request
        Call<List<Cliente>> call = service.getClientes(3);

        call.enqueue(new Callback<List<Cliente>>() {

            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if (response.isSuccessful())
                {
                    if (response != null){
                        List<Cliente> ClienteModelList = response.body();
                        //check if database has information
                        int Clientscount= ( clienteViewModel.getAllClientes().getValue() == null) ? 0 : clienteViewModel.getAllClientes().getValue().size();
                        if(Clientscount>0)
                        {
                            Log.v(TAG, "Base de datos actualizada data ["+Clientscount+"] -["+ClienteModelList.size()+"]");
                            fisrtStateAnimation();
                            return;
                        }
                        for (int i = 0; i < ClienteModelList.size(); i++)
                        {
                            int custumerID = ClienteModelList.get(i).getCustomerId();
                            String nombre= ClienteModelList.get(i).getNombre();
                            String apellido= ClienteModelList.get(i).getApellido();
                            String direccion= ClienteModelList.get(i).getDireccion();
                            String telefono= ClienteModelList.get(i).getTelefono();
                            String estado= ClienteModelList.get(i).getEstado();
                            Log.v(TAG, "recuperado"+nombre+"-"+apellido+"-"+direccion+"-"+telefono);
                            Cliente cliente = new Cliente(custumerID,nombre,apellido,direccion,telefono,estado);
                            clienteViewModel.insert(cliente);
                        }
                    }//end for
                }

                fisrtStateAnimation();
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                Log.v(TAG, t.getMessage());
                fisrtStateAnimation();
            }


        });
    }




}
