package model;

import lombok.*;

import java.io.Serializable;

/**
 * Created on: 20-Dec-19, 00:05
 *
 * @author: Katy Bucșa
 */

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loc implements Serializable {

    private int idSpectacol;
    private int idCategorie;
    private int nrTotalLocuri;
    private int nrLocuriLibere;
}
