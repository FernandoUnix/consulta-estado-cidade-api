package com.consulta.estado.cidade.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.consulta.estado.cidade.models.Cidade;
import com.consulta.estado.cidade.models.UF;
import com.consulta.estado.cidade.utils.Log;

@Service
public class ConsultaCidadesService {

	@Autowired
	private ConsultaEstadosService consultaEstadosService;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${consulta.cidades.url}")
	private String url;

	public List<Cidade> getCidades() {

		Log.getLog().info("Inicio consultar cidades");

		List<UF> estados = this.consultaEstadosService.getEstados();
		List<Cidade> listaCidades = new ArrayList<Cidade>();

		estados.forEach(estado -> {
			listaCidades.addAll(montarCidadesPorEstado(estado));
		});

		Log.getLog().info("Fim consultar cidades");

		return listaCidades;
	}

	private List<Cidade> montarCidadesPorEstado(UF estado) {

		List<Cidade> listaCidades = new ArrayList<Cidade>();

		Log.getLog().info("Inicio consulta de cidades para o estado : " + estado);

		Map<String, String> param = new HashMap<String, String>();
		param.put("UF", estado.getSigla());

		Log.getLog().info("Consultando cidades da UF:  " + estado.getSigla());

		Cidade[] cidades = restTemplate.getForObject(url, Cidade[].class, param);

		listaCidades.addAll(Arrays.asList(cidades));

		Log.getLog().info("Fim consulta de cidades para o estado : " + estado);
		Log.getLog().info("Cidades encontradas para o Estado " + estado.getSigla() + " | " + listaCidades);

		return listaCidades;
	}
}
