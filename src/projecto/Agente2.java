/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto;

import java.util.*;

/**Classe de objectos do tipo Agente2
 *
 * @author: Pedro Silva - 2007183130
 */
public class Agente2 extends Agente{
    
    public Agente2(String cor,String forma,int x,int y,int cvisao){
        super(cor,forma,x,y,cvisao);
    }
    
    /**Método com a estratégia do objecto mais perto.
     * Chama o método visao para saber que objectos estão no seu campo de visão.
     * Caso não haja nenhum objecto no campo de visão move-se aleatóriamente, se
     * houver move-se para o objecto mais perto das suas coordenadas actuais.
     * No fim adiciona o objecto escolhido a sua memória e vai para as suas
     * coordenadas.
     * 
     * @param objectos - Lista de todos os objectos no ambiente
     * @param xm - Valor máximo de x da matriz
     * @param ym - Valor máximo de y da matriz
     */
    public void estrategia(ArrayList<Objecto> objectos,int xm,int ym){
        int indice=0;
        double dist;
        
        visao(objectos);
        
        if(lista_cvisao.isEmpty()==true){
            movimento_aleatorio(xm,ym);
            percurso.add(coordenadas);
        }
        else{
            dist=distancia(coordenadas.getX(),coordenadas.getY(),lista_cvisao.get(0).coordenadas.getX(),lista_cvisao.get(0).coordenadas.getY());
            for(int i=0;i<lista_cvisao.size();i++){
                if(distancia(coordenadas.getX(),coordenadas.getY(),lista_cvisao.get(i).coordenadas.getX(),lista_cvisao.get(i).coordenadas.getY())<dist){
                    indice=i;
                    dist=distancia(coordenadas.getX(),coordenadas.getY(),lista_cvisao.get(i).coordenadas.getX(),lista_cvisao.get(i).coordenadas.getY());
                }
            }
            lista_objectos.add(lista_cvisao.get(indice));
            coordenadas=lista_cvisao.get(indice).coordenadas;
            percurso.add(coordenadas);
        }
    }
    
    public String toString(){
        return "\nAgente do Tipo 2\nEstratégia usada: Mover-se para o objecto visto mais próximo"+super.toString()+"\n";
    }
}
