package br.com.postech.parking.user.application.gateway;

import br.com.postech.parking.user.domain.User;

public interface UserGateway {

    /***
     * CRUD de Usuário
     */
    User createUser(User user);
}
