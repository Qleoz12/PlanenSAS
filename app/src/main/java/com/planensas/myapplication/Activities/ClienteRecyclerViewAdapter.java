package com.planensas.myapplication.Activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.planensas.myapplication.Entities.Cliente;
import com.planensas.myapplication.R;
import com.planensas.myapplication.utils.DiffCallbackImpl;

import java.util.ArrayList;
import java.util.List;



public class ClienteRecyclerViewAdapter extends RecyclerView.Adapter<ClienteRecyclerViewAdapter.ViewHolder>
{

    private List<Cliente> mclientes = new ArrayList<>();
    private OnItemClickListener listener;
    private OnItemClickListener listenerStatus;
    private OnItemClickListener listenerDelete;

    //update with dagger
    private Context context;


    public ClienteRecyclerViewAdapter() {

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cliente_item, parent, false);
        context=parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //iconns
        Iconify.with(new FontAwesomeModule());
        final Cliente cliente = mclientes.get(position);
        holder.nombre.setText(cliente.getNombre());
        holder.apellidos.setText(cliente.getApellido());
        holder.address.setText(cliente.getDireccion());
        holder.phone.setText(cliente.getTelefono());
        holder.ImageView.setImageDrawable(setEstado(cliente.getEstado()));
        holder.imageDelete.setImageDrawable(new IconDrawable( context,FontAwesomeIcons.fa_trash_o).colorRes(R.color.md_red_A200));

    }

    //better way than notifyDataChanged
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
        private final ImageView ImageView;
        private final ImageView imageDelete;


        public ViewHolder(View itemView) {
            super(itemView);
            nombre =    itemView.findViewById(R.id.text_name);
            apellidos = itemView.findViewById(R.id.text_lastname);
            address =   itemView.findViewById(R.id.text_address);
            phone =     itemView.findViewById(R.id.text_phone);
            ImageView=  itemView.findViewById(R.id.imageStatus);
            imageDelete=itemView.findViewById(R.id.imageDelete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(mclientes.get(position));
                    }
                }
            });

            imageDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listenerDelete != null && position != RecyclerView.NO_POSITION) {
                        listenerDelete.onItemClick(mclientes.get(position));
                    }
                }
            });

            ImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listenerStatus != null && position != RecyclerView.NO_POSITION) {
                        listenerStatus.onItemClick(mclientes.get(position));
                    }
                }
            });
        }
    }
    //


    public void setMclientes(List<Cliente> mclientes) {

        this.mclientes = mclientes;
        notifyDataSetChanged();
    }

    //listeners
    public interface OnItemClickListener {
        void onItemClick(Cliente cliente);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public void setOnStatusItemClickListener(OnItemClickListener listener) { this.listenerStatus = listener;    }
    public void setOnDeleteItemClickListener(OnItemClickListener listener) { this.listenerDelete = listener; }

    public Drawable setEstado(int  estado) {

      switch (estado)
      {
          case 0:
              return new IconDrawable( context,FontAwesomeIcons.fa_clock_o).colorRes(R.color.statePending);
          case 1:
              return new IconDrawable( context,FontAwesomeIcons.fa_check).colorRes(R.color.stateApproved);

          case 2:
              return new IconDrawable( context,FontAwesomeIcons.fa_check_circle_o).colorRes(R.color.stateAccepted);

          case 3:
              return new IconDrawable( context,FontAwesomeIcons.fa_flag).colorRes(R.color.stateRejected);

          case 4:
              return new IconDrawable( context,FontAwesomeIcons.fa_share).colorRes(R.color.stateDisabled);
              default:
      }
        return  new IconDrawable( context,FontAwesomeIcons.fa_ban).colorRes(R.color.stateRejected);
    }
}

