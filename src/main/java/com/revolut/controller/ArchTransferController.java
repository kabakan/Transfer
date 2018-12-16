package com.revolut.controller;

import com.revolut.api.ArchTransferService;
import com.revolut.exception.RevolutException;
import com.revolut.model.Transfer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API of archive transfer
 * @author Kanat K.B.
 */
@Api(value = " ArchTransfer", description = "Settings  ArchTransfer")
@RestController
@CrossOrigin
@RequestMapping("/api/archTransfer")
public class ArchTransferController {
    private static final Logger LOG = LogManager.getLogger(ArchTransferController.class);

    @Autowired
    ArchTransferService archTransferService;

    @ApiOperation(value = "Get ArchTransfer by accCode")
    @RequestMapping(value="/get", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Transfer>> getTransfer(@RequestParam(value ="fromAccCode", required = false) String fromAccCode,
                                                      @RequestParam(value ="toAccCode", required = false) String toAccCode) {
        try {
            return  ResponseEntity.ok(archTransferService.get(fromAccCode, toAccCode));
        } catch (RevolutException ex) {
            LOG.error("ERROR: ArchTransfer.get "+ex.getMessage());
            return null;
        }
    }

    @ApiOperation(value = "Get all ArchTransfer")
    @RequestMapping(value="/getAll", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<List<Transfer>> getAll() {
        try {
            return  ResponseEntity.ok(archTransferService.getAll());
        } catch (RevolutException ex) {
            LOG.error("ERROR: ArchTransfer.get "+ex.getMessage());
            return null;
        }
    }


}
