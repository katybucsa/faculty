package model;

import lombok.*;

import java.io.Serializable;

/**
 * Created on: 20-Dec-19, 00:04
 *
 * @author: Katy Bucșa
 */

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categorie implements Serializable {

    private int id;
    private int pret;
}
