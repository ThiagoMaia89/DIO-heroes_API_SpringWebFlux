package com.simplesoftware.heroes_api.document;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

@Data
@NoArgsConstructor
@DynamoDBTable(tableName = "Heroes_Table")
public class Heroes {
    @Id
    @DynamoDBHashKey(attributeName = "id")
    private String id;

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    @DynamoDBAttribute(attributeName = "universe")
    private String universe;

    @DynamoDBAttribute(attributeName = "films")
    private int films;

    public Heroes(String id, String name, String universe, int films) {
        this.id = id;
        this.name = name;
        this.universe = universe;
        this.films = films;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniverse() {
        return universe;
    }

    public void setUniverse(String universe) {
        this.universe = universe;
    }

    public Integer getFilms() {
        return films;
    }

    public void setFilms(Integer films) {
        this.films = films;
    }
}
