package com.zpepdi.qj_airhammer.service;

import com.zpepdi.qj_airhammer.entity.AirHammer;
import com.zpepdi.qj_airhammer.result.Result;

public interface AirHammerService {
    Result compute(AirHammer airHammer);
}
