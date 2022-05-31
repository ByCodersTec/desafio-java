class FinanceReportsController < ApplicationController

  before_action :set_finance_report, only: [:show, :destroy]
  before_action :recalculate_store_balance, only: [:destroy]

  def index
    @finance_reports = FinanceReport.all
  end

  def show
    @finance_report_movement = @finance_report.finance_movements
    @stores_for_select = @finance_report_movement.pluck(:store_name).uniq
    if params[:store_filter]
      @finance_report_movement = @finance_report_movement.where(store_name: params[:store_filter] ) unless params[:store_filter] == ""
      @filtered_store = params[:store_filter]
    else
      @filtered_store = ""
    end
  end

  def new
    @finance_report = FinanceReport.new
  end

  def create
    @finance_report = FinanceReport.create(title: params[:finance_title])

    errors = []
    File.foreach(params[:finance_file]) do |line|
      attributes = helpers.finance_movement_constructor(line)
      finance_movement = FinanceMovement.new(attributes)
      if finance_movement.save
        next
      else
        errors << finance_movement
      end
    end

    respond_to do |format|
      if errors.empty?
        format.html { redirect_to finance_reports_path, notice: 'Relatório Criado com sucesso' }
        format.json { render json: @finance_report, status: :created, location: @finance_report }
      else
        format.html { render action: "new" }
        format.json { render json: errors, status: :unprocessable_entity }
      end
    end
  end

  def destroy
    @finance_report.destroy
    respond_to do |format|
      format.html { redirect_to finance_reports_url, notice: "Relatório excluido com sucesso" }
      format.json { head :no_content }
    end
  end

  private

  def recalculate_store_balance
    helpers.recalculate_store_balance
  end

  def set_finance_report
    @finance_report = FinanceReport.find(params[:id])
  end
end
