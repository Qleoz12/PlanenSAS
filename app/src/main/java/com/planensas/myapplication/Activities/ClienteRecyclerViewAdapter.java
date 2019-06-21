package com.planensas.myapplication.Activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.planensas.myapplication.Entities.Cliente;
import com.planensas.myapplication.R;
import com.planensas.myapplication.utils.DiffCallbackImpl;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ClienteRecyclerViewAdapter extends RecyclerView.Adapter<ClienteRecyclerViewAdapter.ViewHolder>
{

    private List<Cliente> mclientes = new ArrayList<>();
    private OnItemClickListener listener;


    public ClienteRecyclerViewAdapter(List<Cliente> employeeList) {
        this.mclientes.addAll(employeeList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.cliente_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Cliente cliente = mclientes.get(position);
        holder.nombre.setText(cliente.getNombre());
        holder.apellidos.setText(cliente.getApellido());
        holder.address.setText(cliente.getDireccion());
        holder.phone.setText(cliente.getTelefono());
    }

    public void updateEmployeeListItems(List<Cliente> clientes) {
        final DiffCallbackImpl diffCallbackImpl = new DiffCallbackImpl(this.mclientes, clientes);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallbackImpl);



        this.mclientes.clear();
        this.mclientes.addAll(clientes);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public int getItemCount() {
        return mclientes.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder
    {

        private final TextView nombre;
        private final TextView apellidos;
        private final TextView address;
        private final TextView phone;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.text_name);
            apellidos = (TextView) itemView.findViewById(R.id.text_lastname);
            address = (TextView) itemView.findViewById(R.id.text_address);
            phone = (TextView) itemView.findViewById(R.id.text_phone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(mclientes.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Cliente note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
