package org.QA.cucumber.objects;

import java.util.List;
import java.util.Map;

public class ObjectsStored {

    public String getPokemon() {
        return pokemon;
    }

    public void setPokemon(String pokemon) {
        this.pokemon = pokemon;
    }

    private String pokemon;
    private String pokemonType;

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    private String ability;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;
    private String abilityName;
    private List<Map<String, Object>> pokemonStats;

    public List<Map<String, Object>> getPokemonStats() {
        return pokemonStats;
    }

    public void setPokemonStats(List<Map<String, Object>> pokemonStats) {
        this.pokemonStats = pokemonStats;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }
    private  List<Map<String, Object>> pokemonList;

    public List<Map<String, Object>> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(List<Map<String, Object>> pokemonList) {
        this.pokemonList = pokemonList;
    }

    public String getPokemonType() {
        return pokemonType;
    }

    public void setPokemonType(String pokemonType) {
        this.pokemonType = pokemonType;
    }
}
