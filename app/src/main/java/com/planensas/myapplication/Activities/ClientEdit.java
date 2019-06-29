package com.planensas.myapplication.Activities;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.planensas.myapplication.Entities.Cliente;
import com.planensas.myapplication.Entities.Estado;
import com.planensas.myapplication.R;
import com.planensas.myapplication.ViewModels.ClienteViewModel;
import com.planensas.myapplication.ViewModels.EstadosViewModel;
import com.planensas.myapplication.utils.UtilData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ClientEdit extends BaseActivity {
    private ClienteViewModel clienteViewModel;
    private EstadosViewModel estadoViewModel;
    private int  customerId;
    private Button SaveEdit;
    private int positionspiner;

    //views
    @BindView(R.id.ClientNameEdit) EditText name;
    @BindView(R.id.ClientLastNameEdit) EditText lastname;
    @BindView(R.id.ClientAddressEdit) EditText address;
    @BindView(R.id.ClientPhoneEdit) EditText phone;
    @BindView(R.id.spinner) Spinner spin;
    //data
    ArrayAdapter adapter;
    ArrayList<String> arrayList = new ArrayList<>();


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

    private Boolean ValidateClienteEdit()
    {
        if (name.getText().toString().isEmpty()) {
            name.setError(getText(R.string.ErrorName));
            name.requestFocus();
            return false;
        }

        if (lastname.getText().toString().isEmpty()) {
            lastname.setError(getText(R.string.ErrorLastName));
            lastname.requestFocus();
            return false;
        }
        if (address.getText().toString().isEmpty()) {
            address.setError(getText(R.string.ErrorAdress));
            address.requestFocus();
            return false;
        }
        if (phone.getText().toString().isEmpty()) {
            phone.setError(getText(R.string.ErrorTelefono));
            phone.requestFocus();
            return false;
        }
        return true;
    }
    private void GuardarClienteEdit()
    {
        if(ValidateClienteEdit())
        {
            clienteViewModel.getCurreCliente().setNombre(name.getText().toString());
            clienteViewModel.getCurreCliente().setApellido(lastname.getText().toString());
            clienteViewModel.getCurreCliente().setDireccion(address.getText().toString());
            clienteViewModel.getCurreCliente().setTelefono(phone.getText().toString());
            clienteViewModel.update(clienteViewModel.getCurreCliente());
            Intent intent = new Intent(ClientEdit.this,ClientList.class);
            startActivity(intent);
        }
        else
        {

        }

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

               clienteViewModel.setCurreCliente(cliente);
               Log.v("XXXXXXXXXX","estado   "+clienteViewModel.getCurreCliente().getEstado());


           }
       });
        //spinner

        estadoViewModel = ViewModelProviders.of(ClientEdit.this).get(EstadosViewModel.class);
        estadoViewModel.getAllEstados().observe( this, new Observer<List<Estado>>() {
            @Override
            public void onChanged(@Nullable List<Estado> estados)
            {
                if(clienteViewModel.getCurreCliente()!=null)
                {
                    for (Estado estado: estados)
                    {
                        arrayList.add(estado.getEstado());
                        if( clienteViewModel.getCurreCliente().getEstado()==estado.getStateId())
                        {
                            positionspiner=arrayList.indexOf(estado.getEstado());

                        }

                    }
                    Log.v("XXXXXXXXXX2","estado   "+clienteViewModel.getCurreCliente().getEstado()+" positionspiner"+positionspiner);
                    adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1 ,arrayList);
                    adapter.notifyDataSetChanged();
                    spin.setAdapter(adapter);
                    spin.setSelection(positionspiner, false);
                }



            }
        });

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                estadoViewModel.getEstadoByName(arrayList.get(position)).observe( ClientEdit.this, new Observer<Estado>() {

                    @Override
                    public void onChanged(@Nullable Estado estado) {
                        Log.v("selecionado","--1 "+ clienteViewModel.getCurreCliente().getEstado());
                        clienteViewModel.getCurreCliente().setEstado(estado.getStateId());
                        Log.v("selecionado","--2 "+ clienteViewModel.getCurreCliente().getEstado());

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //phone
        phone.setTransformationMethod(null);
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
