//
//  stocksStruct.swift
//  Midterm
//
//  Created by Owen Reynolds on 2/22/22.
//

import SwiftUI

struct stocksStruct: View {
    @ObservedObject var data = getSymbolData()
    
    var body: some View {
        VStack {
            Text(String(data.stock.response.aapl.current))
        }
    }
}

struct stocksStruct_Previews: PreviewProvider {
    static var previews: some View {
        stocksStruct()
    }
}

struct stockLayout: Codable {
    let success: Int
    let response: stockResponseLayout
}
struct stockResponseLayout: Codable {
    let portfolio: stockPortfolioLayout
    let aapl, amzn, dis, dji, fb, googl, hmbl, inx, ixic, msft, rut, tsla, empty: stockNormalLayout

    enum CodingKeys: String, CodingKey {
        case portfolio = "Portfolio"
        case aapl = "AAPL"
        case amzn = "AMZN"
        case dis = "DIS"
        case dji = ".DJI"
        case fb = "FB"
        case googl = "GOOGL"
        case hmbl = "HMBL"
        case inx = ".INX"
        case ixic = ".IXIC"
        case msft = "MSFT"
        case rut = "RUT"
        case tsla = "TSLA"
        case empty = ""
    }
}
struct stockNormalLayout: Codable {
    let current: Double
    let change: Double
    let changepct: Double
    let high: Double
    let low: Double
    let op: Double
    let close: Double
    let high52: Double
    let low52: Double
    let volume: Int
    let marketcap: Int
    let logo: String
    let desc: String
    
    enum CodingKeys: String, CodingKey {
        case current, change, changepct, high, low
        case op = "open"
        case close, high52, low52, volume, marketcap, logo, desc
    }
}
struct stockPortfolioLayout: Codable {
    let current: Double
    let change: Double
    let changepct: Double
    let high: Double
    let low: Double
    let op: Double
    let close: Double
    let high52: Double
    let low52: Double
    let total: Double
    
    enum CodingKeys: String, CodingKey {
        case current, change, changepct, high, low
        case op = "open"
        case close, high52, low52, total
    }
}

class getSymbolData: ObservableObject {
    @Published var stock = stockLayout(success: 0, response: stockResponseLayout(portfolio: stockPortfolioLayout(current: 0.0, change: 0.0, changepct: 0.0, high: 1.0, low: 0.0, op: 0.0, close: 0.0, high52: 1.0, low52: 0.0, total: 0.0), aapl: stockNormalLayout(current: 0.0, change: 0.0, changepct: 0.0, high: 1.0, low: 0.0, op: 0.0, close: 0.0, high52: 1.0, low52: 0.0, volume: 0, marketcap: 0, logo: "", desc: ""), amzn: stockNormalLayout(current: 0.0, change: 0.0, changepct: 0.0, high: 1.0, low: 0.0, op: 0.0, close: 0.0, high52: 1.0, low52: 0.0, volume: 0, marketcap: 0, logo: "", desc: ""), dis: stockNormalLayout(current: 0.0, change: 0.0, changepct: 0.0, high: 1.0, low: 0.0, op: 0.0, close: 0.0, high52: 1.0, low52: 0.0, volume: 0, marketcap: 0, logo: "", desc: ""), dji: stockNormalLayout(current: 0.0, change: 0.0, changepct: 0.0, high: 1.0, low: 0.0, op: 0.0, close: 0.0, high52: 1.0, low52: 0.0, volume: 0, marketcap: 0, logo: "", desc: ""), fb: stockNormalLayout(current: 0.0, change: 0.0, changepct: 0.0, high: 1.0, low: 0.0, op: 0.0, close: 0.0, high52: 1.0, low52: 0.0, volume: 0, marketcap: 0, logo: "", desc: ""), googl: stockNormalLayout(current: 0.0, change: 0.0, changepct: 0.0, high: 1.0, low: 0.0, op: 0.0, close: 0.0, high52: 1.0, low52: 0.0, volume: 0, marketcap: 0, logo: "", desc: ""), hmbl: stockNormalLayout(current: 0.0, change: 0.0, changepct: 0.0, high: 1.0, low: 0.0, op: 0.0, close: 0.0, high52: 1.0, low52: 0.0, volume: 0, marketcap: 0, logo: "", desc: ""), inx: stockNormalLayout(current: 0.0, change: 0.0, changepct: 0.0, high: 1.0, low: 0.0, op: 0.0, close: 0.0, high52: 1.0, low52: 0.0, volume: 0, marketcap: 0, logo: "", desc: ""), ixic: stockNormalLayout(current: 0.0, change: 0.0, changepct: 0.0, high: 1.0, low: 0.0, op: 0.0, close: 0.0, high52: 1.0, low52: 0.0, volume: 0, marketcap: 0, logo: "", desc: ""), msft: stockNormalLayout(current: 0.0, change: 0.0, changepct: 0.0, high: 1.0, low: 0.0, op: 0.0, close: 0.0, high52: 1.0, low52: 0.0, volume: 0, marketcap: 0, logo: "", desc: ""), rut: stockNormalLayout(current: 0.0, change: 0.0, changepct: 0.0, high: 1.0, low: 0.0, op: 0.0, close: 0.0, high52: 1.0, low52: 0.0, volume: 0, marketcap: 0, logo: "", desc: ""), tsla: stockNormalLayout(current: 0.0, change: 0.0, changepct: 0.0, high: 1.0, low: 0.0, op: 0.0, close: 0.0, high52: 1.0, low52: 0.0, volume: 0, marketcap: 0, logo: "", desc: ""), empty: stockNormalLayout(current: 0.0, change: 0.0, changepct: 0.0, high: 1.0, low: 0.0, op: 0.0, close: 0.0, high52: 1.0, low52: 0.0, volume: 0, marketcap: 0, logo: "", desc: "")))
    
    init() {
        loadStocks()
    }
    
    func loadStocks() {
        let url = URL(string: "https://collections.owenclayreynolds.com/scripts/server/data.php?module=stocks")
        guard let requestUrl = url else { fatalError() }
        var request = URLRequest(url: requestUrl)
        request.httpMethod = "GET"
        URLSession.shared.dataTask(with: request) { (data, response, error) in
            do {
                // Check if Error took place
                if let error = error {
                    print("Error took place \(error)")
                    return
                }
                
                // Read HTTP Response Status code
                if let response = response as? HTTPURLResponse {
                    print("Response HTTP Status code: \(response.statusCode)")
                }
                
                // Convert HTTP Response Data to a simple String
                if let data = data {
                    let decodedLists: stockLayout = try JSONDecoder().decode(stockLayout.self, from: data)
                    DispatchQueue.main.async {
                        self.stock = decodedLists
                    }
                }
            } catch {
                print("Error")
            }
        }.resume()
    }
}
