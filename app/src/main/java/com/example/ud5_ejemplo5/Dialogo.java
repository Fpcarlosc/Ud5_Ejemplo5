package com.example.ud5_ejemplo5;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Dialogo extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // "Inflamos" el Fragment
        View view =inflater.inflate(R.layout.fragment_dialogo, container, false);

        final EditText editText = view.findViewById(R.id.editText);
        Button botonAceptar = view.findViewById(R.id.botonAceptar);
        Button botonCancelar = view.findViewById(R.id.botonCancelar);

        // Al pulsar sobre el botón "Aceptar" actualizamos el layout de MainActivity con el texto escrito.
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = editText.getText().toString();

                if(!texto.equals("")){
                    ((MainActivity)getActivity()).texto.setText(texto);

                    // Cerramos el diálogo.
                    getDialog().dismiss();
                }
                else{
                    Toast.makeText(getActivity(), "Introduce el texto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Al pulsar sobre el botón "Cancelar" cerramos el diálogo.
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        return view;
    }
}
