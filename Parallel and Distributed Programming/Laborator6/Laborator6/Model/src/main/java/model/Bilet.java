package model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created on: 20-Dec-19, 00:06
 *
 * @author: Katy Bucșa
 */

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bilet implements Serializable {

    private int id;
    private Spectacol spectacol;
    private int idCategorie;
    private int numar;
    private Date dataVanzare;

    public Bilet(Spectacol spectacol, int idCategorie, int numar, Date dataVanzare) {
        this.spectacol = spectacol;
        this.idCategorie = idCategorie;
        this.numar = numar;
        this.dataVanzare = dataVanzare;
    }
}
