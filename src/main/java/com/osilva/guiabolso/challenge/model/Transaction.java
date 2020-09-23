package com.osilva.guiabolso.challenge.model;

import lombok.*;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(exclude = "duplicate")
public class Transaction {

    private String description;

    private Long date;

    private Integer value;

    @Setter
    private Boolean duplicate;
}
