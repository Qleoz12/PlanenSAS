package com.planensas.myapplication.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "estados_table")
public class Estado
{
        @PrimaryKey
        @NonNull
        private int stateId;
        @NonNull
        private String estado;
        private String description;

        public Estado(int stateId,String estado, String description) {
                this.stateId = stateId;
                this.estado = estado;
                this.description = description;
        }

        public int getStateId() {
                return stateId;
        }

        public void setStateId(int stateId) {
                this.stateId = stateId;
        }

        @NonNull
        public String getEstado() {
                return estado;
        }

        public void setEstado(@NonNull String estado) {
                this.estado = estado;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }
}
