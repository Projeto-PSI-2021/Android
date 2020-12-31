package com.example.tophotels.modelos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TophotelsBDHelper extends SQLiteOpenHelper {
    private static final String NOME_BD = "tophotelsDB";
    private static final int VERSAO_BD = 1;

    private static final String TABELA_REGIAO = "Regiao";
    private static final String ID_REGIAO = "id";
    private static final String NOME_REGIAO = "nome";

    private static final String TABELA_USER = "User";
    private static final String ID_USER = "id";
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String ACCESS_TOKEN = "access_token";

    private static final String TABELA_RESERVA = "Reserva";
    private static final String ID_RESERVA = "id";
    private static final String N_PESSOAS = "n_pessoas";
    private static final String PRECO = "preco";
    private static final String DATA_CHECKIN = "data_checkin";
    private static final String DATA_CHECKOUT = "data_checkout";
    private static final String ESTADO = "estado";
    private static final String HOTEL = "hotel";
    private static final String USER_ID = "user_id";


    private final SQLiteDatabase basedados;

    public TophotelsBDHelper(@Nullable Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
        basedados = this.getWritableDatabase();
    }

    @SuppressLint("SQLiteString")
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTabela_Regiao = "CREATE TABLE " + TABELA_REGIAO + "(" +
                ID_REGIAO + " INTEGER PRIMARY KEY, " +
                NOME_REGIAO + " TEXT NOT NULL" + ")";

        String sqlTabela_User = "CREATE TABLE " + TABELA_USER + "(" +
                ID_USER + " INTEGER PRIMARY KEY, " +
                USERNAME + " TEXT NOT NULL, " +
                EMAIL + " TEXT NOT NULL, " +
                ACCESS_TOKEN + " TEXT NOT NULL" + ")";

        String sqlTabela_Reservas = "CREATE TABLE " + TABELA_RESERVA + "(" +
                ID_RESERVA + " INTEGER PRIMARY KEY, " +
                N_PESSOAS + " INTEGER NOT NULL, " +
                PRECO + " DOUBLE NOT NULL, " +
                DATA_CHECKIN + " STRING NOT NULL, " +
                DATA_CHECKOUT + " STRING NOT NULL, " +
                ESTADO + " STRING NOT NULL, " +
                HOTEL + " STRING NOT NULL, " +
                USER_ID + " STRING NOT NULL" + ")";

        db.execSQL(sqlTabela_Regiao);
        db.execSQL(sqlTabela_User);
        db.execSQL(sqlTabela_Reservas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_REGIAO);
    }

    // ** REGIAO ** //

    public Regiao adicionaRegiao(Regiao regiao) {
        ContentValues valores = new ContentValues();
        valores.put(ID_REGIAO, regiao.getId());
        valores.put(NOME_REGIAO, regiao.getNome());

        long id = this.basedados.insert(TABELA_REGIAO, null, valores);

        if (id > -1) {
            regiao.setId((int) id);
            return regiao;
        }
        return null;
    }

    public void removerAllRegioesBD() {
        this.basedados.delete(TABELA_REGIAO, null, null);
    }

    public ArrayList<Regiao> getAllRegioesBD() {
        ArrayList<Regiao> lista = new ArrayList<>();

        Cursor cursor = this.basedados.query(TABELA_REGIAO, new String[]{ID_REGIAO, NOME_REGIAO},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Regiao regiao = new Regiao(0,
                        cursor.getString(1)
                );
                lista.add(regiao);
            } while (cursor.moveToNext());
        }
        return lista;
    }

    // ** END REGIAO ** //

    // ** USER ** //

    public User adicionaUser(User user) {
        ContentValues valores = new ContentValues();
        valores.put(ID_USER, user.getId());
        valores.put(USERNAME, user.getUsername());
        valores.put(EMAIL, user.getEmail());
        valores.put(ACCESS_TOKEN, user.getAccess_token());

        long id = this.basedados.insert(TABELA_USER, null, valores);

        if (id > -1) {
            user.setId((int) id);
            return user;
        }
        return null;
    }

    public void removerAllUsersBD() {
        this.basedados.delete(TABELA_USER, null, null);
    }

    public ArrayList<User> getAllUsersBD() {
        ArrayList<User> lista = new ArrayList<>();

        Cursor cursor = this.basedados.query(TABELA_USER, new String[]{ID_USER, USERNAME, EMAIL, ACCESS_TOKEN},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User(0,
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        null
                );
                lista.add(user);
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public User getUserDB(String username) {
        Cursor cursor = this.basedados.query(TABELA_USER, new String[]{ID_USER, USERNAME, EMAIL, ACCESS_TOKEN},
                "WHERE username = " + username, null, null, null, null);

        if (cursor.moveToFirst()) {
            return new User(0,
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    null
            );
        }
        return null;
    }

    // ** END USER ** //

    // ** RESERVA ** //

    public Reserva adicionaReserva(Reserva reserva) {
        ContentValues valores = new ContentValues();
        valores.put(ID_RESERVA, reserva.getId());
        valores.put(N_PESSOAS, reserva.getnPessoas());
        valores.put(PRECO, reserva.getPreco());
        valores.put(DATA_CHECKIN, reserva.getDataCheckIn());
        valores.put(DATA_CHECKOUT, reserva.getDataCheckOut());
        valores.put(ESTADO, reserva.getEstado());
        valores.put(HOTEL, reserva.getHotel());
        valores.put(USER_ID, reserva.getUserInfoId());

        long id = this.basedados.insert(TABELA_RESERVA, null, valores);

        if (id > -1) {
            reserva.setId((int) id);
            return reserva;
        }
        return null;
    }

    public void removerAllReservasBD() {
        this.basedados.delete(TABELA_RESERVA, null, null);
    }

    public ArrayList<Reserva> getReservasBD(int userInfoId) {
        ArrayList<Reserva> lista = new ArrayList<>();

        Cursor cursor = this.basedados.query(TABELA_RESERVA, new String[]{ID_RESERVA, N_PESSOAS, PRECO, DATA_CHECKIN, DATA_CHECKOUT, ESTADO, HOTEL, USER_ID},
                "WHERE userInfoId = " + userInfoId, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Reserva reserva = new Reserva(0,
                        cursor.getInt(1),
                        cursor.getDouble(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getInt(7)
                );
                lista.add(reserva);
            } while (cursor.moveToNext());
        }
        return lista;
    }

    // ** END RESERVA ** //
}
