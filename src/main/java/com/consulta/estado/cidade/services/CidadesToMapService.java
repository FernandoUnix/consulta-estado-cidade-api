package com.consulta.estado.cidade.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consulta.estado.cidade.models.Cidade;
import com.consulta.estado.cidade.utils.Log;

@Service
public class CidadesToMapService {

	@Autowired
	private ConsultaCidadesService consultaCidadesService;

	public List<Map<String, String>> getMapResult() {

		Log.getLog().info("Inicio construção Map consulta");

		List<Map<String, String>> listaMapDados = new ArrayList<Map<String, String>>();

		List<Cidade> listaCidades = consultaCidadesService.getCidades();
		
		listaCidades.forEach(x -> {

			Map<String, String> mapDados = new HashMap<String, String>();

			String cidade = x.getNome();
			String uf = x.getMicrorregiao().getMesorregiao().getUf().getSigla();

			mapDados.put("idEstado", x.getMicrorregiao().getMesorregiao().getUf().getId().toString());
			mapDados.put("siglaEstado", uf);
			mapDados.put("regiaoNome", x.getMicrorregiao().getMesorregiao().getUf().getRegiao().getNome());
			mapDados.put("nomeCidade", cidade);
			mapDados.put("nomeMesorregiao", x.getMicrorregiao().getMesorregiao().getNome());
			mapDados.put("nomeFormatado", cidade + "/" + uf);

			listaMapDados.add(mapDados);
			Log.getLog().info("Map consulta adicionado: " + mapDados);
		});

		Log.getLog().info("Finalizado construção Map consulta");

		return listaMapDados;
	}
}
