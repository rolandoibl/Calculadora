package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import java.math.BigDecimal;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    TextView txtvRespuesta;
    float[] numeros;
    char operador;
    boolean puntoDecimal,operacion,respuesta;
    String[] cadenas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtvRespuesta = findViewById(R.id.txtvRespuestaVertical);
        onClickCorregir(getCurrentFocus());


    }

    public void onClick(View v){
        if(respuesta){
            onClickCorregir(getCurrentFocus());
            txtvRespuesta.setText("");
        }
        else if (txtvRespuesta.getText().toString().equals("0")){
            txtvRespuesta.setText("");
        }

        switch (v.getId()){
            case R.id.btnCeroVertical:
                txtvRespuesta.append("0");
                break;
            case R.id.btnUnoVertical:
                txtvRespuesta.append("1");
                break;
            case R.id.btnDosVertical:
                txtvRespuesta.append("2");
                break;
            case R.id.btnTresVertical:
                txtvRespuesta.append("3");
                break;
            case R.id.btnCuatroVertical:
                txtvRespuesta.append("4");
                break;
            case R.id.btnCincoVertical:
                txtvRespuesta.append("5");
                break;
            case R.id.btnSeisVertical:
                txtvRespuesta.append("6");
                break;
            case R.id.btnSieteVertical:
                txtvRespuesta.append("7");
                break;
            case R.id.btnOchoVertical:
                txtvRespuesta.append("8");
                break;
        }
    }

    public void onClickOperacion(View v){
        if(operacion){
            operacion = false;
            puntoDecimal = true;
            switch(v.getId()){
                case R.id.btnSumaVertical:
                    txtvRespuesta.append("+");
                    operador = '+';
                    break;
                case R.id.btnMenosVertical:
                    txtvRespuesta.append("-");
                    operador = '-';
                    break;
                case R.id.btnMultiplicarVertical:
                    txtvRespuesta.append("x");
                    operador = 'x';
                    break;
                case R.id.btnDivisionVertical:
                    txtvRespuesta.append("/");
                    operador = '/';
                    break;
                case R.id.btnCuadrado:
                    respuesta = true;
                    numeros[0] = Float.parseFloat(txtvRespuesta.getText().toString());
                    txtvRespuesta.setText(String.valueOf(numeros[0]*numeros[0]));
                    operador = ' ';
                    break;
                case R.id.btnCubo:
                    respuesta = true;
                    numeros[0] = Float.parseFloat(txtvRespuesta.getText().toString());
                    txtvRespuesta.setText(String.valueOf((numeros[0]*numeros[0]*numeros[0])));
                    operador = ' ';
                    break;
                case R.id.btnFraccion:
                    respuesta = true;
                    numeros[0] = Float.parseFloat(txtvRespuesta.getText().toString());
                    try {
                        if (numeros[0] >= 0) {
                            txtvRespuesta.setText(String.valueOf(Math.sqrt(numeros[0])));
                        }
                    }
                    catch(Exception e){
                        Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                    operador = ' ';
                    break;
                case R.id.btnExp:
                    respuesta = true;
                    numeros[0] = Float.parseFloat(txtvRespuesta.getText().toString());
                    txtvRespuesta.setText(String.valueOf((Math.exp(numeros[0]))));
                    operador = ' ';
                    break;
                case R.id.btnLog:
                    respuesta = true;
                    numeros[0] = Float.parseFloat(txtvRespuesta.getText().toString());
                    try {
                        if(numeros[0]>0)
                        txtvRespuesta.setText(String.valueOf((Math.log10(numeros[0]))));
                    }
                    catch (Exception e){
                        Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                        operador = ' ';
                    break;
                case R.id.btnCoseno:
                    respuesta = true;
                    numeros[0] = Float.parseFloat(txtvRespuesta.getText().toString());
                    txtvRespuesta.setText(String.valueOf((Math.cos(numeros[0]))));
                    operador = ' ';
                    break;
                case R.id.btnSeno:
                    respuesta = true;
                    numeros[0] = Float.parseFloat(txtvRespuesta.getText().toString());
                    txtvRespuesta.setText(String.valueOf((Math.sin(numeros[0]))));
                    operador = ' ';
                    break;

            }
        }
        else{
            Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickCorregir(View v){
        respuesta = false;
        puntoDecimal = true;
        operacion = true;
        operador = ' ';
        numeros = new float[2];
        cadenas = new String[2];
        txtvRespuesta.setText("0");
    }
    public void onClickIgual(View v){
        if (txtvRespuesta.getText().toString().equals("0")){
            txtvRespuesta.setText("0");
        }
        else if(operador==' '){
            Toast.makeText(MainActivity.this,"No hay operación",Toast.LENGTH_SHORT).show();
        }
        else if(txtvRespuesta.getText().toString().endsWith("+") || txtvRespuesta.getText().toString().endsWith("-") || txtvRespuesta.getText().toString().endsWith("/") || txtvRespuesta.getText().toString().endsWith("x") ){
            Toast.makeText(MainActivity.this,"Operación incompleta",Toast.LENGTH_SHORT).show();
        }
        else{
            respuesta = true;
            cadenas = txtvRespuesta.getText().toString().split(Pattern.quote(Character.toString(operador)));
            for(int i = 0; i<2; i++){
                numeros[i] = Float.parseFloat(cadenas[i]);
            }

            switch (operador){
                case '+':
                    txtvRespuesta.setText(String.valueOf(numeros[0]+numeros[1]));
                    break;
                case '-':
                    txtvRespuesta.setText(String.valueOf(numeros[0]-numeros[1]));
                    break;
                case 'x':
                    txtvRespuesta.setText(String.valueOf(numeros[0]*numeros[1]));
                    break;
                case '/':
                    try {
                        if (numeros[1] != 0) {
                            txtvRespuesta.setText(String.valueOf(numeros[0] / numeros[1]));
                        }
                    }
                    catch(Exception e){
                        Toast.makeText(MainActivity.this,"División entre cero inexistente",Toast.LENGTH_SHORT).show();
                    }
                    break;

            }
            operador = ' ';
        }
    }


    public void onClickPunto(View v){
        if(respuesta){
            onClickCorregir(getCurrentFocus());
            puntoDecimal = false;
            txtvRespuesta.append(".");
        }
        else if(puntoDecimal){
            txtvRespuesta.append(".");
            puntoDecimal =  false;
        }
        else{
            Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
        }
    }
}
