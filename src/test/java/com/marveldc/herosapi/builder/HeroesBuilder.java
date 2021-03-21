package com.marveldc.herosapi.builder;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.marveldc.herosapi.document.Heroes;
import lombok.Builder;

@Builder
public class HeroesBuilder {
    @Builder.Default
    private String id = "65";

    @Builder.Default
    private String name = "Batman";

    @Builder.Default
    private String universe = "DC Comics";

    @Builder.Default
    private int films = 5;

    public Heroes toHeroes() {
        return new Heroes(id, name, universe, films);
    }

}
