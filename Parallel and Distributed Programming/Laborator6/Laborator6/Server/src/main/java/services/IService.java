package services;

import model.Bilet;
import model.Loc;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created on: 19-Dec-19, 21:51
 *
 * @author: Katy Bucșa
 */

public interface IService {

    List<Loc> findAllLocuri();

    Bilet buyTicket(int idSpectacol, int idCategorie) throws ExecutionException, InterruptedException;

    void makeVerification();
}