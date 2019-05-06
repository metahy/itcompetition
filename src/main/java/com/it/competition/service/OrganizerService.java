package com.it.competition.service;

import com.it.competition.domain.Organizer;

public interface OrganizerService {

    Organizer login(Organizer organizer);

    Organizer getOrganizer(Integer organizerId);
}
