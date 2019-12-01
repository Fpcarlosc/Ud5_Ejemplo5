# Ud5_Ejemplo5
_Ejemplo 5 de la Unidad 5._ 

Vamos a implementar una aplicación en la que haremos uso de _DialogFragment_. De esta clase heredará el _Fragment_ Dialogo que crearemos y 
que inflará el _layout_ creado para el diálogo y gestionará los eventos de los botones asociados.
La aplicación consistirá en mostrar un diálogo al pulsar sobre un botón del _MainActivity_ donde en él podremos escribir un texto que al aceptar se
mostrará en el _MainActivity_.

Para ello tenemos que seguir los siguientes pasos:

## Paso 1: Creación de los _layouts_
 
El primer paso será crear los _layouts_ tanto para el _MainActivity_ como para el diálogo creado:

### _activity_main.xml_

```
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/textView2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/texto"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintHorizontal_bias="0.279"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/textoDialogo"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.19"
        app:layout_constraintStart_toEndOf="@+id/textView2"
        app:layout_constraintTop_toTopOf="parent"
        tools:text="Texto del dialogo" />

    <Button
        android:id="@+id/botonAbrir"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/abrir"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView2" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
Donde simplemente tenemos dos _TextViews_ y un botón para abrir el diálogo.
### _fragment_dialogo.xml_ 
```
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".Dialogo">

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Introduce el texto:"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.328" />

    <EditText
        android:id="@+id/editText"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:hint="Texto"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView"
        app:layout_constraintVertical_bias="0.07999998" />

    <Button
        android:id="@+id/botonAceptar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/aceptar"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.188"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/editText"
        app:layout_constraintVertical_bias="0.501" />

    <Button
        android:id="@+id/botonCancelar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/cancelar"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toEndOf="@+id/botonAceptar"
        app:layout_constraintTop_toBottomOf="@+id/editText" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
En este ejemplo se ha creado un diálogo con un _EditText_ y dos botones, uno para aceptar el texto introducido y mostrarlos en 
el _MainActivity_ y otro para cancelar la acción. Es interesante ver que en el diálogo podemos usar cualquier _View_.

## Paso 2: Creación de la clase _Dialogo_

Una vez tenemos el diálogo diseñado podemos implementar su clase asociada. Ésta extenderá de la clase _DialogFragment_ y tendrá que
 sobreescribir el método _onCreateView_ para poder inflar el _layout_ asociado y gestionar los eventos de los botones: si pulsamos 
  sobre el botón _Aceptar_ escribiremos el texto en el _MainActivity_ y cerraremos el diálogo si éste no es vacío, si lo es mostramos 
  un mensaje al usuario, y si pulsamos en el botón _Cancelar_ simplemente cerramos el diálogo.
 ```
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
```
## Paso 3: Implementación de _MainActivity.java_

En el _MainActivity_, al pulsar sobre el botón _Abrir_ crearemos un objeto de nuestra clase _Dialogo_ y lo mostramos haciendo uso 
del método _show_ al que le pasamos el método _getSupportFragmentManager_ y su etiqueta asociada.
```
public class MainActivity extends AppCompatActivity {

    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = findViewById(R.id.textoDialogo);
        Button botonAbrir = findViewById(R.id.botonAbrir);

        // Al pulsar el botón "Abrir" creamos nuestro Dialogo (que es un Fragment) y lo mostramos.
        botonAbrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialogo dialogo = new Dialogo();
                dialogo.show(getSupportFragmentManager(), "DialogoFragment");
            }
        });
    }
}
```

