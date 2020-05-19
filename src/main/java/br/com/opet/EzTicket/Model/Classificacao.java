package br.com.opet.EzTicket.Model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import br.com.opet.EzTicket.Utils.Utils;

public enum Classificacao {

	LIVRE(1, new String[] {
			"Violência: Violência fantasiosa; presença de armas sem violência; mortes sem violência; ossadas e esqueletos sem violência.",
			"Sexo e Nudez: Nudez não erótica; ou mesmo com a presença de nudez; sem a presença de conteúdo sexual.",
			"Drogas: Consumo moderado ou insinuado de drogas lícitas sem relevância para a obra."
	}), 
	MENORES_DE_DEZ(2, new String[] {
			"Violência: Presença de armas com intuito de violência; medo/tensão; angústia; ossadas e esqueletos com resquícios de ato de violência; atos criminosos sem violência; linguagem depreciativa.",
			"Sexo e Nudez: Conteúdos educativos sobre sexo.",
			"Drogas: Descrições verbais do consumo de drogas lícitas; discussão sobre o tema “tráfico de drogas”; uso medicinal de drogas ilícitas."
	}), 
	MENORES_DE_DOZE(3, new String[] {
			"Violência: Ato violento; lesão corporal; descrição de violência; presença de sangue; sofrimento da vítima; morte natural ou acidental com violência; ato violento contra animais; exposição ao perigo; exposição de pessoas em situações constrangedoras ou degradantes; agressão verbal; obscenidade; bullying; exposição de cadáver; assédio sexual; supervalorização da beleza física; supervalorização do consumo; morte derivada de ato heróico; violência psicológica.",
			"Sexo e Nudez: Nudez velada; insinuação sexual; carícias sexuais; masturbação não explícita; linguagem obscena/palavrões; linguagem de conteúdo sexual; simulações de sexo; apelo sexual.",
			"Drogas: Consumo de drogas lícitas; indução ao consumo de drogas lícitas; consumo irregular de medicamentos; menção a drogas ilícitas; discussão sobre \"Legalização de Drogas ilícitas\"."
	}), 
	MENORES_DE_QUATORZE(4, new String[] {
			"Violência: Morte intencional; preconceito; exploração sexual; aborto; eutanásia; pena de morte.",
			"Sexo e Nudez: Nudez moderada; erotização; vulgaridade; relação sexual; prostituição.",
			"Drogas: Insinuação do consumo de drogas ilícitas; descrições verbais do consumo e tráfico de drogas ilícitas."
	});

	private int id;
	private String[] desc;

	Classificacao(int id, String[] desc) {
		this.id = id;
		this.desc = desc;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return Utils.capitalize(this.name().toLowerCase());
	}

	public List<String> getDescription() {
		return Arrays.asList(this.desc);
	}

	public static Classificacao getClassificacaoById(int id) {
		Optional<Classificacao> result = Arrays.asList(values()).stream().filter(s -> s.getId() == id).findFirst();
		return result.isPresent() ? result.get() : LIVRE;
	}

}
