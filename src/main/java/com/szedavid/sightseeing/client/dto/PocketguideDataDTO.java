package com.szedavid.sightseeing.client.dto;

import com.szedavid.sightseeing.dto.TourDTO;
import lombok.Data;

import java.util.List;

@Data
public class PocketguideDataDTO {
    List<TourDTO> tours;
}
