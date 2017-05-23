package com.example.elisabeth.jogodavelha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class ActMain extends AppCompatActivity {
    //Constante da tag de cada botão
    //Essa constante recupera o botão através do método getQuadrado
    private final String QUADRADO = "quadrado";
    //Constante que vai ser impressa no text do botão
    private final String BOLA = "O";
    //Constante que vai ser impressa no text do botão
    private final String XIS = "X";

    //Guardamos o ultimo valor jogado
    private String lastplay = "X";

    //Guarda a instancia da nossa view
    private View view;

    //matriz que define as confições para o jogo acabar;
    int[][] estadoFinal = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},

            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9},

            {1, 5, 9},
            {3, 5, 7},
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Inflando o XML e guardando a instância de View
        setView( getLayoutInflater().inflate( R.layout.activity_act_main, null));
        //Estamos passando a instância para nossa Activity
        setContentView( getView() );
    }

    public void Verificar(View v){
        if(!Acabou()) {//verifica se o jogo acabou
            if (((Button) v).getText().equals("")) {//Verifica se o texto que esta vindo da variavel v é diferente de vazio
                if (getLastPlay().equals(XIS)) {//Verifica se o ultimo valor jogado é igual a X
                    ((Button) v).setText(BOLA);//Jogamos BOLA
                    setLastPlay(BOLA);//Seta lastPlay como 'O', para que da proxima vez ele não entre nessa condição
                } else {
                    ((Button) v).setText(XIS);//Jogamos XIS
                    setLastPlay(XIS);//Seta lastPlay como 'X', para que da proxima vez ele não entre nessa condição
                }
            } else {
                //Imprimir mensagem dizendo que essa posição ja foi jogada
                Toast.makeText(getView().getContext(), "Opa!!! Escolha outro quadrado.", Toast.LENGTH_LONG).show();
            }
            Acabou();//Verificamos se é o fim do jogo
        }
    }

    public void NovoJogo (View v){

        setEnableQuadrado(true);//Ativar Quadrados
        setColorBlack();//Coloca todos as letras de preto novamente
        LimpaCampos();//Chamando método para limpar todos os quadrados
        RadioButton rX = (RadioButton)getView().findViewById(R.id.rbX);
        RadioButton rO = (RadioButton)getView().findViewById(R.id.rbO);

        if(rX.isChecked()){//Verifica se X esta checado
            setLastPlay(BOLA);//altera a ultima jogada para 'O', começamos com a proxima jogada que seria 'X'
        }else{
            if(rO.isChecked()){//Verifica se O esta checado
                setLastPlay( XIS );//altera a ultima jogada para 'X', começamos com a proxima jogada que seria 'O'
            }
        }
    }

    public void LimpaCampos(){
        for(int i = 1; i<=9; ++i){//Percorrer todos os quadrados
            if(getQuadrado(i)!=null){//Verifica se o quadrado é diferente de vazio
                getQuadrado(i).setText("");//Coloca todos os quadrados como vazio
            }
        }
    }

    public void setEnableQuadrado (boolean b){
        for(int i = 1; i<=9; ++i){//Percorre todos os quadrados
            if(getQuadrado(i)!=null){//Verifica se o quadrado é diferente de vazio
                getQuadrado(i).setEnabled( b );//Permite que os quadrados sejam selecionados, após clicar em Jogar
            }
        }
    }

    public void setColorQuadrados (int btn, int colorX){
        //Recebe o num. do botão pela var btn
        //Passa a instância da nossa cor pela variável colorX
        getQuadrado(btn).setTextColor(getResources().getColor(colorX));
        //Recuperamos o botão e colocamos textColor com a cor passada pela variável X
    }

    public Button getQuadrado(int tagNum){
        //Se tagNum = 1, retornamos o qudrado 1
        //Se tagNum = 9, retornamos o qudrado 9
        return (Button)getView().findViewWithTag(QUADRADO+tagNum);
    }

    public void setColorBlack(){
        for(int i=0; i<=9; ++i){//Percorrer todos os qudrados
            if(getQuadrado( i )!=null){//Verificar se o quadrado é diferente de vazio
                setColorQuadrados(i, R.color.black);//Envia a cor preta para o método SetColorQuadrados
            }
        }
    }


    public boolean Acabou(){

        //Vamos percorrer todas as condições definidas na matriz
        for(int x=0; x<=7; ++x){
            String p1 = getQuadrado(estadoFinal[x][0]).getText().toString();
            String p2 = getQuadrado(estadoFinal[x][1]).getText().toString();
            String p3 = getQuadrado(estadoFinal[x][2]).getText().toString();

            //Verificar se p1, p2, p3 são diferentes de vazio
            //Se não verificar o jogo acaba na jogada 1
            //Todos os quadrados iniciais são vazios
            if( (!p1.equals("") ) &&
                (!p2.equals("") ) &&
                (!p3.equals("") ) ){

                if( p1.equals( p2 ) && ( p2.equals(p3) ) ){
                    //caso os 3 botões sejam iguais, pintar de vermelho
                    setColorQuadrados( estadoFinal[x][0], R.color.red);
                    setColorQuadrados( estadoFinal[x][1], R.color.red);
                    setColorQuadrados( estadoFinal[x][2], R.color.red);
                    //indicamos que o jogo acabou
                    Toast.makeText(getView().getContext(), "Fim de jogo", Toast.LENGTH_LONG).show();
                    return true; //O jogo acabou
                }
            }
        }
        return false;
    }
    public View getView(){
        return view;
    }
    
    public void setView (View view){
        this.view = view;
    }

    public String getLastPlay(){
        return lastplay;
    }
    public void setLastPlay (String lastplay){
        this.lastplay = lastplay;
    }

}
