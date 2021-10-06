/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.managed_beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.fusalmo.www.entities.TokensEntity;
import org.fusalmo.www.model.TokensModel;

/**
 *
 * @author Soporte
 */
@ManagedBean
@RequestScoped
public class TokensBean {
    TokensModel modelo= new TokensModel();
    private TokensEntity token;
    private TokensEntity selectedToken;
    private List<TokensEntity> listaTokens;

    /**
     * Creates a new instance of TokensBean
     */
    public TokensBean() {
    }
    
    public List<TokensEntity> getListaTokens(){
        return modelo.listarTokens();
    }

    public TokensEntity getSelectedToken() {
        return selectedToken;
    }

    public void setSelectedToken(TokensEntity selectedToken) {
        this.selectedToken = selectedToken;
    }
    
}
