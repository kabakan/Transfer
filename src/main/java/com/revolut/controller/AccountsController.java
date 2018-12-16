package com.revolut.controller;

import com.revolut.api.AccountsService;
import com.revolut.exception.RevolutException;
import com.revolut.model.Accounts;
import com.revolut.model.Currency;
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
import java.util.Map;

/**
 * API of accounts
 * @author Kanat K.B.
 */

@Api(value = "Accounts", description = "Settings accounts")
@RestController
@CrossOrigin
@RequestMapping("/api/accounts")
public class AccountsController {
    private static final Logger LOG = LogManager.getLogger(AccountsController.class);

    @Autowired
    AccountsService accountsService;

    @Autowired
    BodyResponse bodyResponse;

    @ApiOperation(value = "Create accounts")
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ResponseEntity<BodyResponse> add(@RequestBody Accounts accounts ) {
        try {
            return ResponseEntity.ok(accountsService.add(accounts));
        } catch (Exception ex) {
            LOG.error("ERROR: ServiceAccounts.add "+ex.getMessage());
            return ResponseEntity.ok(bodyResponse.set("FAIL","Add account"));
        }
    }

    @ApiOperation(value = "Update accounts")
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public ResponseEntity<BodyResponse> update(@RequestBody Accounts accounts ) {
        try {
            return ResponseEntity.ok(accountsService.update(accounts));
        } catch (Exception ex) {
            LOG.error("ERROR: ServiceAccounts.update "+ex.getMessage());
            return ResponseEntity.ok(bodyResponse.set("FAIL","update account"));
        }
    }

    @ApiOperation(value = "Delete accounts")
    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public ResponseEntity<BodyResponse> delete(@RequestParam(value ="id", required = false) Integer id) {
        try {
            return ResponseEntity.ok(accountsService.delete(id));
        } catch (Exception ex) {
            LOG.error("ERROR: ServiceAccounts.delete "+ex.getMessage());
            return ResponseEntity.ok(bodyResponse.set("FAIL","Delete account"));
        }
    }

    @ApiOperation(value = "Get accounts by accCode")
    @RequestMapping(value="/get", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<List<Accounts>> getAccounts(@RequestParam(value ="clientName", required = false) String clientName,
                                                       @RequestParam(value ="accCode", required = false) String accCode) {
        try {
            return  ResponseEntity.ok(accountsService.get(clientName, accCode));
        } catch (RevolutException ex) {
            LOG.error("ERROR: ServiceAccounts.get "+ex.getMessage());
            return null;
        }
    }

    @ApiOperation(value = "Get all accounts")
    @RequestMapping(value="/getAll", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<List<Accounts>> getAll() {
        try {
            return  ResponseEntity.ok(accountsService.getAll());
        } catch (RevolutException ex) {
            LOG.error("ERROR: ServiceAccounts.get "+ex.getMessage());
            return null;
        }
    }

    @ApiOperation(value = "Get all currency")
    @RequestMapping(value="/getCurrency", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<List<String>> getCurrency() {
        try {
            Currency currency = new Currency();
            return  ResponseEntity.ok(currency.getCurrCode());
        } catch (Exception ex) {
            LOG.error("ERROR: GetCurrecny "+ex.getMessage());
            return null;
        }
    }

    @ApiOperation(value = "Getenerate account code")
    @RequestMapping(value="/generate", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<Map<String,String>> getnereateAcc() {
        try {
            return  ResponseEntity.ok(accountsService.generate());
        } catch (Exception ex) {
            LOG.error("ERROR: GetCurrecny "+ex.getMessage());
            return null;
        }
    }
}
