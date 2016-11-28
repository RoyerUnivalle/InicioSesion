package com.example.userasus.iniciosesion.data;

import android.provider.BaseColumns;

/**
 * Created by UserAsus on 27/11/2016.
 */
public class ContractUsuarios {

    public  static abstract class UsuarioEntry implements BaseColumns{

        public static final String TABLE_NAME ="usuarios";

        public static final String ID = "usuario_id";
        public static final String NAME = "nombre";

    }

}
