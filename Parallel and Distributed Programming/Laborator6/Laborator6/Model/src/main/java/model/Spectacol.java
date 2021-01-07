package model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created on: 19-Dec-19, 23:21
 *
 * @author: Katy Bucșa
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Data
public class Spectacol implements Serializable {

    private int id;
    private Date data;
    private String titlu;
    private String descriere;
}
