package com.revolut.controller;

import com.revolut.api.TransferService;
import com.revolut.exception.RevolutException;
import com.revolut.model.Transfer;
import com.revolut.utils.BodyResponse;
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
 * API of transfer
 * @author Kanat K.B.
 */
@Api(value = "Transfer", description = "Settings Transfer")
@RestController
@CrossOrigin
@RequestMapping("/api/transfer")
public class TransferController {
    private static final Logger LOG = LogManager.getLogger(TransferController.class);

    @Autowired TransferService transferService;

    @Autowired
    BodyResponse bodyResponse;

    @ApiOperation(value = "Create transfer")
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ResponseEntity<BodyResponse> add(@RequestBody Transfer transfer) {
        try {
            return ResponseEntity.ok(transferService.add(transfer));
        } catch (RevolutException ex) {
            LOG.error("ERROR: transfer.add "+ex.getMessage());
            return ResponseEntity.ok(bodyResponse.set("FAIL","Add transfer"));
        }
    }

    @ApiOperation(value = "Send transfer")
    @RequestMapping(value="/send", method = RequestMethod.GET)
    public ResponseEntity<BodyResponse> send() {
        try {
            return ResponseEntity.ok(transferService.sendTransfer());
        } catch (RevolutException ex) {
            LOG.error("ERROR: transfer.send "+ex.getMessage());
            return ResponseEntity.ok(bodyResponse.set("FAIL","Send transfer"));
        }
    }

    @ApiOperation(value = "Get transfer by accCode")
    @RequestMapping(value="/get", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Transfer>> getTransfer(@RequestParam(value ="fromAccCode", required = false) String fromAccCode,
                                                      @RequestParam(value ="toAccCode", required = false) String toAccCode) {
        try {
            return  ResponseEntity.ok(transferService.get(fromAccCode, toAccCode));
        } catch (RevolutException ex) {
            LOG.error("ERROR: transfer.get "+ex.getMessage());
            return null;
        }
    }

    @ApiOperation(value = "Get all transfer")
    @RequestMapping(value="/getAll", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<List<Transfer>> getAll() {
        try {
            return  ResponseEntity.ok(transferService.getAll());
        } catch (RevolutException ex) {
            LOG.error("ERROR: transfer.get "+ex.getMessage());
            return null;
        }
    }

}
