<?php

namespace App\Services;

use App\Templates\DataImportsTemplate;
use Illuminate\Http\UploadedFile;
use Illuminate\Support\Facades\Log;
use Illuminate\Support\Facades\Storage;

class TransactionService
{

    /**
     * Patch File to Upload
     *
     * @var string
     */
    protected $pathFileUpload = 'uploads/imports';

    /**
     * Process upload file
     *
     * @param UploadedFile $file
     * @return string
     */
    public function uploadFile(UploadedFile $file): string
    {
        try {
            
            return Storage::putFile($this->pathFileUpload, $file);

        } catch (\Throwable $th) {
            Log::error('TransactionService - uploadFile error: ' . $th->getMessage());
            throw new $th;
        }
    }

    public function readFile(string $pathFile)
    {
        try {
            
            $data = null;
            $fp = fopen(Storage::path($pathFile), "r");

            while (!feof($fp)) {
                $data[] = fgets($fp);
            }

            fclose($fp);
            return $data;

        } catch (\Throwable $th) {
            Log::error('TransactionService - readFile error: ' . $th->getMessage());
            throw $th;
        }
    }

    /**
     * Parser string row to array data
     *
     * @param array $rows
     * @param string $importType
     * @return array
     */
    public function parserData(array $rows, int $historyId, $importType = 'CNAB'): array
    {
        try {

            $templateImport = new DataImportsTemplate($importType);
            $transactions = [];

            foreach ($rows as $row) {
                if (strlen($row) > 3) {
                    $value =  $templateImport->getField('value', $row);
                    $transactions[] = [
                        'import_history_id'     => $historyId,
                        'type_transaction_id'   => $templateImport->getField('type_transaction_id', $row),
                        'date'                  => $templateImport->getField('date', $row),
                        'value'             => $value,
                        'cpf'               => $templateImport->getField('cpf', $row),
                        'card'              => $templateImport->getField('card', $row),
                        'hour'              => $templateImport->getField('hour', $row),
                        'store_owner'       => $templateImport->getField('store_owner', $row),
                        'store_name'        => $templateImport->getField('store_name', $row),
                        'created_at'        => date('Y-m-d H:i:s'),
                        'updated_at'        => date('Y-m-d H:i:s'),
                    ];
                }
            }

            return $transactions;

        } catch (\Throwable $th) {
            Log::error('TransactionService - parserData error: ' . $th->getMessage());
            throw $th;
        }
    }
}