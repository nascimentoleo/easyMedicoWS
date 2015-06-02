package br.com.easymedicows;

import java.util.Calendar;
import java.util.Vector;

public class Horarios {

	public static Vector<String> calculaHorariosDisponiveisPorDia(
			Vector<String> agendamentos, Medico medico) {
		String horarios;
		if (medico.getAgendaManha().equals("S")
				&& medico.getAgendaTarde().equals("S"))
			horarios = "07:00,08:00,09:00,10:00,11:00,13:00,14:00,15:00,16:00,17:00,18:00";
		else if (medico.getAgendaManha().equals("S"))
			horarios = "07:00,08:00,09:00,10:00,11:00";
		else if (medico.getAgendaTarde().equals("S"))
			horarios = "13:00,14:00,15:00,16:00,17:00,18:00";
		else
			return null;
		// Transformo em um vetor
		String[] vetHorarios = horarios.split(",");
		Vector<String> horariosDisponiveis = new Vector<String>();
		// Percorro os horários pré-definidos
		System.out.println(vetHorarios[4]);
		for (int i = 0; i < vetHorarios.length; i++) {
			int count = 0;
			
			// Agora irei percorrer os agendamentos, para saber quantos
			// agendados eu tenho no horário atual
			for (int j = 0; j < agendamentos.size(); j++) {
				// Se são iguais, significa que existe um paciente agendado para
				// esse horário, então incremento o contador
				if (vetHorarios[i].equals(agendamentos.get(j))) {
					count++;
				}
				// Se já atingiu o limite, saio do for pois já sei que esse
				// horário está indisponível
				if (count == medico.getQtdPacientesPorHora())
					break;
			}
			// Se for menor, adiciono o horário na lista de disponíveis
			if (count < medico.getQtdPacientesPorHora()) {
				horariosDisponiveis.add(vetHorarios[i]);
			}

		}

		return horariosDisponiveis;
	}

}
