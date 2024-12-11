package com.sparta.logistics.product.application.outputport;

import com.sparta.logistics.product.domain.Company;
import java.util.Optional;

public interface HubOutputPort {

    Optional<Company> findCompany(Long userId);
}
