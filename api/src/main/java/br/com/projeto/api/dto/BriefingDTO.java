package br.com.projeto.api.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.projeto.api.model.Briefing;
import br.com.projeto.api.enums.BriefingStatus;
import lombok.Builder;

@Builder
public class BriefingDTO {

    private Long id;
    private String clienteName;
    private String task;
    private String description;
    private BriefingStatus status;
    private LocalDate date;
    private LocalTime time;

    public static BriefingDTO from(Briefing briefing){
        if(briefing == null){
            throw new NullPointerException();
        }
        BriefingDTO briefingDTO = BriefingDTO
                .builder()
                .id(briefing.getId())
                .clienteName(briefing.getCliente().getName())
                .task(briefing.getTask())
                .description(briefing.getDescription())
                .status(briefing.getStatus())
                .date(briefing.getDate())
                .time(briefing.getTime())
                .build();
        return briefingDTO;
    }

    public static List<BriefingDTO> fromAll(List<Briefing> briefingList){
        return briefingList.stream().map(BriefingDTO::from).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getClienteName() {
        return clienteName;
    }
    public void setClienteNome(String clienteName) {
        this.clienteName = clienteName;
    }
    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public BriefingStatus getStatus() {
        return status;
    }
    public void setStatus(BriefingStatus status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }


}
