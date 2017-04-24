/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto;

import java.util.*;

/**Classe de objectos do tipo Agente1
 *
 * @author: Pedro Silva - 2007183130
 */
public class Agente1 extends Agente{
    
    public Agente1(String cor,String forma,int x,int y,int cvisao){
        super(cor,forma,x,y,cvisao);
    }
    
    /**Método com a estratégia aleatória.
     * Chama o método visao para saber que objectos estão no seu campo de visão.
     * Caso não haja nenhum objecto no campo de visão move-se aleatóriamente, se
     * houver move-se aleatóriamente para um objecto no seu campo de visão.
     * No fim adiciona o objecto escolhido a sua memória e vai para as suas
     * coordenadas.
     * 
     * @param objectos - Lista de todos os objectos no ambiente
     * @param xm - Valor máximo de x da matriz
     * @param ym - Valor máximo de y da matriz
     */
    public void estrategia(ArrayList<Objecto> objectos,int xm,int ym){
        visao(objectos);
        Random num=new Random();
        int x;
        
        if(lista_cvisao.isEmpty()==true){
            movimento_aleatorio(xm,ym);
            percurso.add(coordenadas);
        }
        else{
            x=num.nextInt(lista_cvisao.size());
            lista_objectos.add(lista_cvisao.get(x));
            coordenadas=lista_cvisao.get(x).coordenadas;
            percurso.add(coordenadas);
        }
        
    }
    
    public String toString(){
        return "\nAgente do Tipo 1\nEstratégia usada: Mover-se aleatoriamente para um objecto visto"+super.toString()+"\n";
    }
          
}
