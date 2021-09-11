package com.szedavid.sightseeing.client.dto;

import com.szedavid.sightseeing.model.Tour;
import lombok.Data;

import java.util.List;

@Data
public class PocketguideDataDTO {
    List<Tour> tours;
}
