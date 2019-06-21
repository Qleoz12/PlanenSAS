package com.planensas.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.animation.Animator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.planensas.myapplication.Activities.ClientList;
import com.planensas.myapplication.Entities.Cliente;
import com.planensas.myapplication.ViewModels.ClienteViewModel;
import com.planensas.myapplication.services.ClienteRequests;
import com.planensas.myapplication.services.DataServiceGenerator;

import java.util.List;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {
    ClienteViewModel clienteViewModel;
    private static final String TAG = "MyActivity";
    private ImageView bookIconImageView;
    private TextView bookITextView;
    private ProgressBar loadingProgressBar;
    private RelativeLayout rootView, afterAnimationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //views
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //data
        clienteViewModel = ViewModelProviders.of(MainActivity.this).get(ClienteViewModel.class);
        //views
        initViews();
        //data
        fetch();

    }

    private void initViews() {
        bookIconImageView = findViewById(R.id.bookIconImageView);
        bookITextView = findViewById(R.id.bookITextView);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);
        rootView = findViewById(R.id.rootView);
        afterAnimationView = findViewById(R.id.afterAnimationView);
    }

    private void fisrtStateAnimation()
    {
        bookITextView.setVisibility(GONE);
        loadingProgressBar.setVisibility(GONE);
        rootView.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorSplashText));
        bookIconImageView.setImageResource(R.mipmap.ic_logo_gray_plannensas);
        startAnimation();
    }
    private void startAnimation() {
        ViewPropertyAnimator viewPropertyAnimator = bookIconImageView.animate();
        viewPropertyAnimator.x(10f);
        viewPropertyAnimator.y(30f);
        viewPropertyAnimator.setDuration(1500);
        viewPropertyAnimator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                afterAnimationView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    private void fetch()
    {
        //start animation


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
                        if(ClienteModelList.size()==Clientscount)
                        {
                            Log.v(TAG, "Base de datos actualizada data ["+Clientscount+"] -["+ClienteModelList.size()+"]");
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
