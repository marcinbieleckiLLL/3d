package com.greencrane.service;

import com.greencrane.entity.Communication;
import com.greencrane.service._abstract.AbstractService;
import com.greencrane.service._abstract.AbstractServiceImpl;
import org.springframework.stereotype.Service;

public interface CommunicationService extends AbstractService<Communication> {}

@Service("communicationService")
class CommuncationServiceImpl extends AbstractServiceImpl<Communication> implements CommunicationService {}
