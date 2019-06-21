package com.planensas.myapplication.utils;

import com.planensas.myapplication.Entities.Cliente;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

public class DiffCallbackImpl extends DiffUtil.Callback{

        private final List<Cliente> mOldList;
        private final List<Cliente> mNewList;

    public DiffCallbackImpl(List<Cliente> oldList, List<Cliente> newList) {
            this.mOldList = oldList;
            this.mNewList = newList;
        }

        @Override
        public int getOldListSize() {
            return mOldList.size();
        }

        @Override
        public int getNewListSize() {
            return mNewList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            boolean validation=true;
            validation=validation && mOldList.get(oldItemPosition).getCustomerId() == mNewList.get(newItemPosition).getCustomerId();
            return validation;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            boolean validation=true;
            validation=validation && mOldList.get(oldItemPosition).getNombre() == mNewList.get(newItemPosition).getNombre();
            validation=validation && mOldList.get(oldItemPosition).getApellido() == mNewList.get(newItemPosition).getApellido();
            validation=validation && mOldList.get(oldItemPosition).getDireccion() == mNewList.get(newItemPosition).getDireccion();
            validation=validation && mOldList.get(oldItemPosition).getEstado() == mNewList.get(newItemPosition).getEstado();
            validation=validation && mOldList.get(oldItemPosition).getTelefono() == mNewList.get(newItemPosition).getTelefono();
            return validation;
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            // Implement method if you're going to use ItemAnimator
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }
