package pl.sadowski.teaipracadomowatydzien6.MoviesAPI.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



@Getter
@Setter
@AllArgsConstructor
public class Movie {

    @NotNull(message = "NIE PODAWAJ PUSTEJ NAZWY")
    private String name;
    private String producer;


    @Min(value = 1895, message = "pierwszy film został nakręcony w 1895r pt. \"polewacz piany\"")
    @Max(value = 2020, message = "Ten film nie został jeszcze nakręcony")
    private int productionYear;
}
