# frozen_string_literal: true

class AddStoreReferenteToFinanceMovement < ActiveRecord::Migration[6.1]
  def change
    add_reference :finance_movements, :store_financial_movement, null: false, foreign_key: true
  end
end
