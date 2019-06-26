package com.planensas.myapplication.Activities;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.planensas.myapplication.Entities.Cliente;
import com.planensas.myapplication.R;
import com.planensas.myapplication.ViewModels.ClienteViewModel;
import com.planensas.myapplication.utils.UtilData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClientEdit extends BaseActivity {
    private ClienteViewModel clienteViewModel;
    private int  customerId;
    private Button SaveEdit;

    //buterKnife
    @BindView(R.id.ClientNameEdit) EditText name;
    @BindView(R.id.ClientLastNameEdit) EditText lastname;
    @BindView(R.id.ClientAddressEdit) EditText address;
    @BindView(R.id.ClientPhoneEdit) EditText phone;
    @BindView(R.id.ClientStatusEdit)EditText status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //data
        getClient();
        //views
        setupBindings();

        //buton
        SaveEdit=findViewById(R.id.button);
        SaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GuardarClienteEdit();
            }
        });


    }
    public int getLayoutId(){
        return R.layout.activity_client_edit;
    }

    private void GuardarClienteEdit()
    {
        Cliente cliente= new Cliente(customerId,
                name.getText().toString().toString(),
                lastname.getText().toString().toString(),
                address.getText().toString().toString(),
                phone.getText().toString().toString(),
                status.getText().toString().toString());
        clienteViewModel.update(cliente);
        Intent intent = new Intent(ClientEdit.this,ClientList.class);
        startActivity(intent);
    }

    private void setupBindings() {


        clienteViewModel = ViewModelProviders.of(ClientEdit.this).get(ClienteViewModel.class);
        //get client of Viewmodel
       clienteViewModel.getCliente(customerId).observe(this, new Observer<Cliente>() {

           @Override
           public void onChanged(@Nullable Cliente cliente) {
               name.setText(cliente.getNombre());
               lastname.setText(cliente.getApellido());
               address.setText(cliente.getDireccion());
               phone.setText(cliente.getTelefono());
               status.setText(cliente.getEstado());

               clienteViewModel.setCurreCliente(cliente);


           }
       });





    }

    public void getClient()
    {
        //get the current intent
        Intent intent = getIntent();
        //get the attached extras from the intent
        //we should use the same key as we used to attach the data.
        customerId = (int) intent.getIntExtra(UtilData.EXTRA_ID,0);
    }



}
