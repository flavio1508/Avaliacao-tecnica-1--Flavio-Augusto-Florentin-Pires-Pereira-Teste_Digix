package br.desafio.digix.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Familia {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long Id;
    @OneToOne
    private ResponsavelFamilia responsavelFamilia;
    @OneToMany
    private List<Dependentes> dependentes;
    private int rendaTotalFamilia;
    private int pontuacao;

    public Familia(ResponsavelFamilia responsavelFamilia, List<Dependentes> dependentes, int rendaTotalFamilia,
            int pontuacao) {
        this.responsavelFamilia = responsavelFamilia;
        this.dependentes = dependentes;
        this.rendaTotalFamilia = rendaTotalFamilia;
        this.pontuacao = pontuacao;
    }

}
