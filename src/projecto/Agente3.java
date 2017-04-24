/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto;

import java.util.*;

/**Classe para objectos do tipo Agente3
 *
 * @author: Pedro Silva - 2007183130
 */
public class Agente3 extends Agente{
    
    public Agente3(String cor,String forma,int x,int y,int cvisao){
        super(cor,forma,x,y,cvisao);
    }
    
    /**Método com a estratégia do objecto mais diferente.
     * Chama o método visao para saber que objectos estão no seu campo de visão.
     * Caso não haja nenhum objecto no campo de visão move-se aleatóriamente, se
     * houver move-se para o objecto mais diferente em relação aos que já estão
     * em memória.
     * No fim adiciona o objecto escolhido a sua memória e vai para as suas
     * coordenadas.
     * 
     * @param objectos - Lista de todos os objectos no ambiente
     * @param xm - Valor máximo de x da matriz
     * @param ym - Valor máximo de y da matriz
     */
    public void estrategia(ArrayList<Objecto> objectos,int xm,int ym){
        int indice=-1,diferencas=-1;
        
        visao(objectos);
        
        if(lista_cvisao.isEmpty()==true){
            movimento_aleatorio(xm,ym);
            percurso.add(coordenadas);
        }
        else{
            for(int i=0;i<lista_cvisao.size();i++){
                if(nDiferencas(lista_cvisao.get(i))>diferencas){
                    indice=i;
                    diferencas=nDiferencas(lista_cvisao.get(i));
                }
            }
            lista_objectos.add(lista_cvisao.get(indice));
            coordenadas=lista_cvisao.get(indice).coordenadas;
            percurso.add(coordenadas);
        }
    }
    /**Método que serve para calcular o número de diferenças entre dois objectos
     * do tipo Objecto.
     * Vão ser comparadas três strings.
     * 
     * @param objc - Objecto que vai ser comparado com os outros em memória
     * @return diferencas - numero total de diferenças do objecto em causa
     */
    private int nDiferencas(Objecto objc){
        int diferencas=3,aux_dif;
        
        for(int i=0;i<lista_objectos.size();i++){
            aux_dif=0;
            if(objc.cor.compareTo(lista_objectos.get(i).cor)==0)
                aux_dif++;
            if(objc.forma.compareTo(lista_objectos.get(i).forma)==0)
                aux_dif++;
            if(objc.getTipo().compareTo(lista_objectos.get(i).getTipo())==0)
                aux_dif++;
            
            if(aux_dif<diferencas)
                diferencas=aux_dif;
        }
        return diferencas;
    }
    
    public String toString(){
        return "\nAgente do Tipo 3\nEstratégia usada: Mover-se para o objecto visto com o maior numero de diferenças"+super.toString()+"\n";
    }
}
